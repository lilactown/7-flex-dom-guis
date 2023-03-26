(ns town.lilac.guis.one
  (:require
   [town.lilac.dom :as d]
   [town.lilac.flex :as flex]
   [town.lilac.flex.dom :as fd]
   [town.lilac.guis.lib :as lib]))

(def counter (flex/source 0))

(defn start!
  []
  (d/div
   {:class "flex gap-4 justify-center content-center p-5"}
   (fd/scope
    (d/div
     {:class "py-1"}
     (d/text @counter)))
   (d/div
    (lib/button
     {:onclick #(counter inc)}
     (d/text "inc")))))
