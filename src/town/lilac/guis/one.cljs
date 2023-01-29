(ns town.lilac.guis.one
  (:require
   [town.lilac.dom :as d]
   [town.lilac.flex :as flex]
   [town.lilac.flex.dom :as fd]))

(def counter (flex/source 0))

(defn start!
  []
  (d/div
   {:class "flex gap-4 justify-center content-center p-5"}
   (fd/track
    (d/div
     {:class "py-1"}
     (d/text @counter)))
   (d/div
    (d/button
     {:class "bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded cursor-pointer"
      :onclick #(counter inc)}
     (d/text "inc")))))
