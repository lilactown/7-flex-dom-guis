(ns town.lilac.guis.three
  (:require
   [town.lilac.dom :as d]
   [town.lilac.flex :as flex]
   [town.lilac.flex.dom :as fd]
   [town.lilac.guis.lib :as lib]))

(defonce flight
  (flex/source {:option :one-way
                :start ""
                :return ""}))

(defn start!
  []
  (d/form
   {:class "p-5 flex flex-col gap-2"
    :onsubmit #(.preventDefault %)}
   (d/select
    {:name "option"
     :onchange #(flight
                 (fn [m]
                   (assoc m :option (keyword (.. % -target -value)))))}
    (d/option {:value "one-way"} (d/text "one-way flight"))
    (d/option {:value "return"} (d/text "return flight")))
   (d/div
    (lib/textbox
     {:type "text"
      :oninput #(flight
                 (fn [m]
                   (assoc m :start (.. % -target -value))))}))
   (d/div
    (lib/textbox
     {:type "text"
      :disabled (or (= :one-way (:option @flight)) nil)
      :oninput #(flight
                 (fn [m]
                   (assoc m :return (.. % -target -value))))}))
   (lib/button
    {:onclick #(js/alert "hi")
     :disabled true}
    (d/text "Book"))))
