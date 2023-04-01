(ns town.lilac.guis.main
  (:require
   [town.lilac.flex :as flex]
   [town.lilac.flex.dom :as d]
   [town.lilac.guis.one :as one]
   [town.lilac.guis.two :as two]
   [town.lilac.guis.three :as three]
   [town.lilac.guis.four :as four]
   [town.lilac.guis.five :as five]))

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
  (prn :start-app)
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
      (menu-item "Counter" #(selected 1) (= 1 @selected))
      (menu-item "Temperature converter" #(selected 2) (= 2 @selected))
      (menu-item "Flight booker" #(selected 3) (= 3 @selected))
      (menu-item "Timer" #(selected 4) (= 4 @selected))
      (menu-item "CRUD" #(selected 5) (= 5 @selected))
      (menu-item "Circle Drawer" nil false)
      (menu-item "Cells" nil false)))
    (case @selected
      1 (one/start!)
      2 (two/start!)
      3 (three/start!)
      4 (four/start!)
      5 (five/start!)))))


(defonce root (d/create-root (js/document.getElementById "root") #(app)))

(defn ^:dev/after-load start!
  []
  (flex/dispose! root)
  (flex/run! root))

(defn init
  []
  (prn :init))
