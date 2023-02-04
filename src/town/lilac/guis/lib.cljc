(ns town.lilac.guis.lib
  (:require
   [town.lilac.dom :as d])
  #?(:cljs (:require-macros [town.lilac.guis.lib])))


(defmacro button
  [& args]
  (let [[opts children] (if (map? (first args))
                          [(first args) (rest args)]
                          [nil args])]
    (prn opts children)
    `(d/button
      {:class ["bg-blue-500"
               "hover:bg-blue-700"
               "disabled:bg-gray-300"
               "text-white"
               "font-bold"
               "py-1"
               "px-2"
               "rounded"
               "cursor-pointer"
               "disabled:cursor-default"
               (:class ~opts)]
       :& (dissoc ~opts :class)}
      ~@children)))