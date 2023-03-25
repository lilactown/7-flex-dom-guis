(ns town.lilac.guis.four
  (:require
   [town.lilac.dom :as d]
   [town.lilac.flex :as flex]
   [town.lilac.flex.dom :as fd]))

(defonce timer
  (flex/source {:end (* 60 1000)
                :value 0}))

(comment
  @timer
  ;; increment by a second
  (timer #(update % :value + 1000))
  ;; reset
  (timer (constantly {:end (* 60 1000)
                      :value 0})))

(def timer-fx
  (flex/effect
   [_]
   (let [id (js/setInterval
             (fn []
               (timer #(if (< (:value %) (:end %))
                         (update % :value + 100)
                         %)))
             100)]
     (prn :timer/start id)
     #(do (prn :timer/cleanup id)
          (js/clearInterval id)))))

(defn start!
  []
  (timer-fx)
  ;; wrap in track so that this outer function doesn't get called again on each
  ;; update of `@timer`, running the `timer-fx` effect again
  (fd/track
   (d/div
    (d/progress {:max (:end @timer)
                 :value (:value @timer)})
    (d/div (d/text (:value @timer)))
    (let [v (:end @timer)
          slider (d/input
                  {:type "range"
                   :value v
                   :max (* 60 5000)
                   :oninput (fn [e]
                              (timer
                               #(assoc % :end (js/parseInt
                                               (.. e -target -value)))))})]
      (when (not= (str v) (.-value slider))
        (set! (.-value slider) v))))))
