;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname manhattan3d) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
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