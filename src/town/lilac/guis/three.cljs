(ns town.lilac.guis.three
  (:require
   [clojure.string :as string]
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
    :onsubmit (fn [e]
                (.preventDefault e)
                (js/alert (pr-str @flight)))}
   (d/select
    {:name "option"
     :onchange #(flight
                 (fn [m]
                   (assoc m :option (keyword (.. % -target -value)))))}
    (d/option
     {:value "one-way"
      :selected (= :one-way (:option @flight))}
     (d/text "one-way flight"))
    (d/option
     {:value "return"
      :selected (= :return (:option @flight))}
     (d/text "return flight")))
   (d/div
    (lib/textbox
     {:type "date"
      :class "w-48"
      :value (:start @flight)
      :oninput #(flight
                 (fn [m]
                   (assoc m :start (.. % -target -value))))}))
   (d/div
    (lib/textbox
     {:type "date"
      :class "w-48"
      :value (:return @flight)
      :disabled (= :one-way (:option @flight))
      :oninput #(flight
                 (fn [m]
                   (assoc m :return (.. % -target -value))))}))
   (lib/button
    {:disabled (or
                (and (= :one-way (:option @flight))
                     (string/blank? (:start @flight)))
                (and (= :return (:option @flight))
                     (or (string/blank? (:start @flight))
                         (string/blank? (:return @flight))
                         (>= (js/Date. (:start @flight))
                             (js/Date. (:return @flight))))))}
    (d/text "Book"))))
