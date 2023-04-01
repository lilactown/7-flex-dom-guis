(ns town.lilac.guis.two
  (:require
   [town.lilac.flex :as flex]
   [town.lilac.flex.dom :as d]
   [town.lilac.guis.lib :as lib]))

(def C (flex/source 0))
(def F (flex/source 32))

(defn update-F!
  [s]
  (flex/batch
   (let [t (js/parseFloat s)]
     (when (js/isNaN t)
       (throw (ex-info "Not a number" {:s s})))
     (C (.toFixed (* (- t 32) (/ 5 9))))
     (F t))))

(defn update-C!
  [s]
  (flex/batch
   (let [t (js/parseFloat s)]
    (when (js/isNaN t)
      (throw (ex-info "Not a number" {:s s})))
    (C t)
    (F (.toFixed (+ 32 (* t (/ 9 5))))))))

(defn numberbox
  "A controlled number input"
  [v on-input]
  (let [el (lib/textbox
            {:class "w-24"
             :type "number"
             :value v
             :oninput #(on-input (.. % -target -value))})]
    (when (not= (str v) (.-value el))
      (set! (.-value el) v))))

(defn start!
  []
  (d/div
   {:class "p-5"}
   (numberbox @C update-C!) (d/text " Celsius = ")
   (numberbox @F update-F!) (d/text " Fahrenheit")))

(comment
  (C 32))
