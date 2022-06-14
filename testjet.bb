(ns testjet
  (:require [clojure.repl :refer [doc]]
            [clojure.edn :as edn]))

(def maptest (edn/read-string (slurp "/Users/frap/Dev/gjson/paekak5/map.edn")))

(defn test-vec-of-maps [input]
  (let [v  (:features input)]
    (for [{:keys [geometry] {:keys [level]} :properties} v]
      ;; (do (assoc v [:properties :level] (+ (Integer/valueOf level) 42) )
      (println level (:type geometry)))
    )
  )


(defn vec-of-maps [v]
  (mapv (fn [{{:keys [level]} :properties :as m}]
          (if level
            ;; (do (println level)
            (assoc-in m [:properties :level] (+ 70 level))
            ;;     )
            m))
        v))


(def changed-level (-> (:features maptest) vec-of-maps))
(spit "/Users/frap/Dev/gjson/paekak5/map-chg.edn" {:type "FeatureCollection" :features changed-level})
(vec-of-maps (:features maptest))

(doc mapv)
(doc edn/read-string)
