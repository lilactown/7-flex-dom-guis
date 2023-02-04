(ns town.lilac.guis.main
  (:require
   [town.lilac.dom :as d]
   [town.lilac.flex :as flex]
   [town.lilac.flex.dom :as fd]
   [town.lilac.guis.one :as one]
   [town.lilac.guis.two :as two]
   [town.lilac.guis.three :as three]))

(defonce selected (flex/source 1))

(defn menu-item
  [text on-click selected?]
  (d/li
   {:class (str "px-4 py-2 hover:bg-blue-500 hover:text-white cursor-pointer"
                (when selected? " bg-blue-300"))
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
   (fd/track
    (d/div
     {:class "flex flex-1"}
     (d/div
      {:class "border-r"}
      (d/ul
       (menu-item "Counter" #(selected 1) (= 1 @selected))
       (menu-item "Temperature converter" #(selected 2) (= 2 @selected))
       (menu-item "Flight booker" #(selected 3) (= 3 @selected))
       (menu-item "Timer" nil false)
       (menu-item "CRUD" nil false)
       (menu-item "Circle Drawer" nil false)
       (menu-item "Cells" nil false)))
     (case @selected
       1 (one/start!)
       2 (two/start!)
       3 (three/start!))))))

(defn ^:dev/after-load start!
  []
  (d/patch (js/document.getElementById "root") app))

(defn init
  []
  (start!))
