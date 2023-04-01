(ns town.lilac.guis.five
  (:require
   [clojure.string :as string]
   [town.lilac.flex :as flex]
   [town.lilac.flex.dom :as d]
   [town.lilac.guis.lib :as lib]))

(defonce db
  (flex/source
   [{:name "Rich" :surname "Hickey"}
    {:name "Ryan" :surname "Carniato"}
    {:name "Juho" :surname "Teperi"}]))

(defonce selected
  (flex/source nil))

(defonce filter-prefix
  (flex/source ""))

(comment @selected)

(defn create!
  [name surname]
  (db #(conj % {:name name :surname surname})))

(defn update!
  [id name surname]
  (db #(assoc % id {:name name :surname surname})))

(defn delete!
  [id]
  (db #(into (subvec % 0 id) (subvec % (inc id)))))

(defn start!
  []
  (d/form
   {:class "p-5 flex flex-col gap-2"
    :id "crud-form"}
   (d/div
    (d/label {:for "filter"} (d/text "Filter prefix: "))
    (lib/textbox {:id "filter"
                  :oninput #(filter-prefix (.. % -target -value))}))
   (d/div
    {:class "flex gap-2"}
    (d/select
     {:class "border p-1 rounded min-w-[200px]"
      :size 5
      :onchange #(selected (js/parseInt (.. % -target -value)))}
     (doseq [[id {:keys [name surname]}] (map-indexed vector @db)
             :when (or (string/blank? @filter-prefix)
                       (string/starts-with? surname @filter-prefix))]
       (d/option
        {:value id
         :selected (= id @selected)}
        (d/text surname ", " name))))
    (d/div
     {:class "flex flex-col gap-1"}
     (d/div
      (d/label (d/text "Name: "))
      (lib/textbox {:id "name"
                    :name "name"
                    :value (get-in @db [@selected :name])}))
     (d/div
      (d/label (d/text "Surname: "))
      (lib/textbox {:id "surname"
                    :name "surname"
                    :value (get-in @db [@selected :surname])}))))
   (d/div
    {:class "flex gap-2"}
    (lib/button
     {:onclick #(let [elements (-> (js/document.getElementById "crud-form")
                                   (.-elements))]
                  (create! (.. elements -name -value)
                           (.. elements -surname -value))
                  (selected nil))
      :type "button"}
     (d/text "Create"))
    (lib/button
     {:disabled (nil? @selected)
      :onclick #(let [elements (-> (js/document.getElementById "crud-form")
                                   (.-elements))]
                  (update! @selected
                           (.. elements -name -value)
                           (.. elements -surname -value))
                  (selected nil))
      :type "button"}
     (d/text "Update"))
    (lib/button
     {:disabled (nil? @selected)
      :onclick (fn [_]
                 (delete! @selected)
                 (selected nil))
      :type "button"}
     (d/text "Delete")))))
