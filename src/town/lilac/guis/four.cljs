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
  (timer #(update % :value + 100)))

(def timer-fx
  (flex/effect
   [_]
   (let [id (js/setInterval
             (fn []
               (timer #(if (< (:value %) (:end %))
                         (update % :value + 100)
                         %)))
             100)]
     (prn :effect/start id)
     #(do (prn :effect/cleanup id)
          (js/clearInterval id)))))

(defn start!
  []
  (timer-fx)
  (fd/track
   (d/div
    (d/div (d/text (:value @timer)))
    (d/input {:type "range"
              :value (:end @timer)
              :max (* 60 5000)
              :oninput (fn [e]
                         (timer #(assoc  % :end (js/parseInt
                                                 (.. e -target -value)))))}))))
