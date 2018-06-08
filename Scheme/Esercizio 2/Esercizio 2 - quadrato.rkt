;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname quadrato) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))



;croce1: parte inferiore della croce
;croce2: parte superiore

;quadrato1: parte superiore
;quadrato2: parte inferiore



(define croce1 (shift-down (glue-tiles (quarter-turn-left smaller-tile)
                           (quarter-turn-left larger-tile)) 3))

(define croce2 (shift-right (shift-down (glue-tiles (quarter-turn-right larger-tile)
                                                    (quarter-turn-right smaller-tile)) 1) 1))

(define croce3 (glue-tiles croce2 (shift-down smaller-tile 5)))

(define croce (glue-tiles croce1 croce3))


(define quadrato1 (shift-right (half-turn (glue-tiles (quarter-turn-right larger-tile)
                                        (quarter-turn-right smaller-tile))) 0.8))
(define quadrato2 (shift-down (glue-tiles (quarter-turn-right larger-tile)
                              (quarter-turn-right smaller-tile)) 1.6))

(define quadrato (glue-tiles quadrato1 quadrato2))

quadrato
croce

