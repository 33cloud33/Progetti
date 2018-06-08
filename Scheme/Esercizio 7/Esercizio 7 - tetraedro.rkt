;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Esercizio 7 - tetraedro|) (read-case-sensitive #t) (teachpacks ((lib "Teachpack_hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "Teachpack_hanoi.ss" "installed-teachpacks")) #f)))




(define prob-3d-1
  (lambda (r v b)
    (cond ((and (> r 0) (> v 0) (> b 0)) (+ (* 1/3 (prob-3d-1 (- r 1) v b))
                                            (* 1/3 (prob-3d-1 r (- v 1) b))
                                            (* 1/3 (prob-3d-1 r v (- b 1)))
                                            ))
          ((and (= r 0) (= v 0)) (* 1/3 (prob-3d-1 r v (- b 1))))
          ((and (= r 0) (= b 0)) (* 1/3 (prob-3d-1 r (- v 1) b)))
          ((and (= v 0) (= b 0)) (* 1/3 (prob-3d-1 (- r 1) v b)))
          ((= r 0) (+ (* 1/3 (prob-3d-1 r (- v 1) b))
                      (* 1/3 (prob-3d-1 r v (- b 1)))
                      ))
          ((= v 0) (+ (* 1/3 (prob-3d-1 (- r 1) v b))
                      (* 1/3 (prob-3d-1 r v (- b 1)))
                      ))
          ((= b 0) (+ (* 1/3 (prob-3d-1 r (- v 1) b))
                      (* 1/3 (prob-3d-1 (- r 1) v b))
                      ))
          (else 1/3)
          )
    ))



(define prob-3d 
  (lambda (n i j) ; j = verdi, i = rosse, n = numero di livelli
    (/ (manhattan-3d i j (- n i j))   ;numero di percorsi diversi
       (expt 3 n))                    ;numero di percorsi possibili
    ))

;manhattan-3d (funzionante!)

(define manhattan-3d
  (lambda (i j k)
    (cond ((tutto-zero i j k) 0)
          ((due-zeri i j k)   1)
          ((x-zero i j k) (+ (manhattan-2d (- i 1) k) (manhattan-2d i (- k 1)) j)) ; se x=0
          ((y-zero i j k) (+ (manhattan-2d (- j 1) k) (manhattan-2d j (- k 1)) i)) ; se y=0
          ((z-zero i j k) (+ (manhattan-2d (- i 1) j) (manhattan-2d i (- j 1)) k)) ; se z=0
          (else (nessun-zero i j k))
          )    
    ))

(define nessun-zero
  (lambda (i j k)
    (+ (manhattan-3d (- i 1) j k) (manhattan-3d i (- j 1) k) (manhattan-3d i j (- k 1)))
    ))

(define x-zero
  (lambda (i j k)
    [and (= j 0)(> i 0)(> k 0)]
    ))

(define y-zero
  (lambda (i j k)
    [and (= i 0)(> j 0)(> k 0)]
    ))

(define z-zero
  (lambda (i j k)
    [and (= k 0)(> i 0)(> j 0)]
    ))

(define tutto-zero
  (lambda (i j k)
    [and (= i 0)(= j 0)(= k 0)]
    ))

(define due-zeri
  (lambda (i j k)
    (or [and (= i 0) (= j 0)]
        [and (= j 0) (= k 0)]
        [and (= i 0) (= k 0)]
        )
    ))
  
(define manhattan-2d
  (lambda (x y)
    (if (or (= x 0) (= y 0))
        1
        (+ (manhattan-2d x (- y 1)) (manhattan-2d (- x 1) y))
        )
    ))

 (prob-3d 1 0 1)
 (prob-3d 3 1 1)
 (prob-3d 6 2 2)