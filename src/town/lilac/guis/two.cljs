(ns town.lilac.guis.two
  (:require
   [town.lilac.dom :as d]
   [town.lilac.flex :as flex]
   [town.lilac.flex.dom :as fd]))

(def C (flex/source 0))

(def F (flex/source 32))

(defn update-F!
  [s]
  (flex/batch
   (let [t (js/parseFloat s)]
     (when (js/isNaN t)
       (throw (ex-info "Not a number" {:s s})))
     (C (* (- t 32) (/ 5 9)))
     (F t))))

(defn update-C!
  [s]
  (flex/batch
   (let [t (js/parseFloat s)]
    (when (js/isNaN t)
      (throw (ex-info "Not a number" {:s s})))
    (C t)
    (F (+ 32 (* t (/ 9 5)))))))

(defn input
  "A controlled number input"
  [v on-input]
  (let [el (d/input
            {:type "number" :value v
             :oninput #(on-input (.. % -target -value))})]
    (when (not= (str v) (.-value el))
      (set! (.-value el) v))))

(defn start!
  []
  (fd/track
   (d/div
    (d/div (input @C update-C!))
    (d/div (input @F update-F!)))))

(comment
  (C 32))
