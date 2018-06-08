;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Esercizio 10 - add-mul-pow|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define H
  (lambda (ident funz)
    (lambda (m n) 
      (cond ((= n 0) (ident m))                  ; (add 5 0) --> 5 perchè ident = 5     (mul 5 0) ---> 0  perchè ident = 0                     (pow 5 0) ---> 1 perchè ident = 1            

            ((= m 0)                             ; (add 0 5) --> 5 perchè ident = 5     (mul 0 5) ---> 0  perchè ident = 0                     (pow 0 5) ---> perchè restituisce 0
             (if (= (ident n) 1)                 ; (add 0 1) --> 1                      (mul non ricade in questo caso perchè (ident n) = 0)   (pow 0 1) ---> 0 
                 (if (= (ident m) 0)             ; perchè (ident m) = 0, quindi 1                                                              perchè (ident m) = 1, quindi 0 
                     1
                     0
                     )
                 (ident n)
                 )                 
             )
            ((= (ident m) m) (funz m (- n 1)))          ; somma
            ((= (ident m) 0) (funz m (mul m (- n 1))))  ; prodotto  
            ((= (ident m) 1) (funz m (pow m (- n 1))))  ; potenza 
        )
      )))

(define s2
  (lambda (u v)
    (if (>= v 0)
        (add (+ u 1) v)
        u
        )
    ))

(define add
  (H (lambda (x) x) s2))

(define mul
  (H (lambda (x) 0) add))

(define pow
  (H (lambda (x) 1) mul))