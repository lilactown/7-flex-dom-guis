(ns town.lilac.guis.four
  (:require
   [town.lilac.flex :as flex]
   [town.lilac.flex.dom :as d]
   [town.lilac.guis.lib :as lib]))

(def initial {:end (* 60 1000)
              :value 0})

(defonce timer
  (flex/source initial))

(comment
  @timer
  ;; increment by a second
  (timer #(update % :value + 1000))
  ;; reset
  (timer (constantly initial)))

(def timer-fx
  )

(defn start!
  []
  (flex/effect
   []
   (let [id (js/setInterval
             (fn []
               (timer #(if (< (:value %) (:end %))
                         (update % :value + 100)
                         %)))
             100)]
     ;; clear interval on dispose
     #(js/clearInterval id)))
  ;; wrap in scope so that this outer function doesn't get called again on each
  ;; update of `@timer`, running the `timer-fx` effect again
  (d/div
   {:class "p-5 flex flex-col gap-2"}
   (d/progress
    {:class ["[&::-webkit-progress-bar]:rounded"
             "[&::-webkit-progress-value]:rounded"
             "[&::-webkit-progress-bar]:bg-slate-300"
             "[&::-webkit-progress-value]:bg-blue-500"
             "[&::-moz-progress-bar]:bg-blue-400"
             "w-full"]
     :max (:end @timer)
     :value (:value @timer)})
   (d/div
    (d/text (.toFixed (/ (:value @timer) 1000) 1) "s"))
   (d/div
    {:class "flex gap-2 items-center"}
    (d/label {:for "duration"} (d/text "Duration:"))
    (let [v (:end @timer)
          slider (d/input
                  {:type "range"
                   :value v
                   :class "h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer"
                   :id "duration"
                   :max (* 60 5000)
                   :oninput (fn [e]
                              (timer
                               #(assoc % :end (js/parseInt
                                               (.. e -target -value)))))})]
      (when (not= (str v) (.-value slider))
        (set! (.-value slider) v))))
   (lib/button
    {:onclick #(timer (constantly initial))}
    (d/text "Reset"))))
