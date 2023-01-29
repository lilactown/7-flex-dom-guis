(ns town.lilac.flex.dom
  (:require
   [town.lilac.flex :as flex]
   [town.lilac.dom :as dom])
  #?(:cljs (:require-macros [town.lilac.flex.dom])))

(defmacro track
  [& body]
  `(let [track-id# (gensym "track")
         pfn# (fn [] ~@body)
         fx# (flex/effect
              [el#]
              (if (some? el#)
                (dom/patch-outer el# pfn#)
                (pfn#)))]
     (fx#)))
