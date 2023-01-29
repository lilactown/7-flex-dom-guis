(ns town.lilac.guis.main
  (:require
   [town.lilac.dom :as d]
   [town.lilac.flex :as flex]
   [town.lilac.flex.dom :as fd]
   [town.lilac.guis.one :as one]
   [town.lilac.guis.two :as two]))

(def selected (flex/source 1))

(defn menu-item
  [text on-click]
  (d/li
   {:class "px-4 py-2 hover:bg-blue-500 hover:text-white cursor-pointer"
    :onclick on-click}
   (d/text text)))

(defn app
  []
  (d/div
   {:class "h-screen flex flex-col"}
   (d/div
    {:class "p-4 border-b"}
    (d/h1
     {:class "text-xl"}
     (d/text "7 GUIs")))
   (d/div
    {:class "flex flex-1"}
    (d/div
     {:class "border-r"}
     (d/ul
      (menu-item "Counter" #(selected 1))
      (menu-item "Temperature converter" #(selected 2))
      (menu-item "Flight booker" nil)
      (menu-item "Timer" nil)
      (menu-item "CRUD" nil)
      (menu-item "Circle Drawer" nil)
      (menu-item "Cells" nil)))
    (fd/track
     (case @selected
       1 (one/start!)
       2 (two/start!))))))

(defn ^:dev/after-load start!
  []
  (d/patch (js/document.getElementById "root") app))

(defn init
  []
  (start!))
