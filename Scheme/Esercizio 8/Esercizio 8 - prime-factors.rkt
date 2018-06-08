;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname |Esercizio 8 - prime-factors|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #t #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))

;parte A: lista ordinata dei fattori primi (OK!)
(define prime-factors
  (lambda (x)
    (let loop ((lista '()) (n x))
      (if (> n 1)
          (cond ((primo? n) (reverse (cons n lista)))
                (else (loop (cons (remainder-rec n 2) lista) (/ n (remainder-rec n 2))))
                )
          "errore"
          )
      )
    ))

;parte B: lista ordinata e senza ripetizioni dei fattori primi (OK!)
(define short-prime-factors
  (lambda (x)
    (let loop ((lista (prime-factors x)) (risultato null))
      (cond ((<= x 1) "errore")
            ((= (length lista) 1) (reverse (cons (first lista) risultato)))
            ((= (first lista) (second lista))
             (loop (rest lista) risultato)) ;se sono uguali (es. 2 2 2) restituisce la lista ridotta (es. 2 2), lasciando invariato il risultato
            (else (loop (rest lista) (cons (first lista) risultato))) ;se non sono uguali (es. 2 5 5) aggiunge 2 al risultato e continua il lavoro             
            )
      )
    ))

;parte C: lista ordinata di coppie (fattore esponente) (OK!)
(define prime-facts-degs
  (lambda (x)
    (let loop ((cifre (short-prime-factors x))
               (risultato null))
      (if (= (length cifre) 0)
          (reverse risultato)       
          (loop (cdr cifre)
                (cons (list (car cifre) (esponente (prime-factors x) (car cifre) 0)) risultato)
                )
          )
      )
    ))

;funzioni ausiliarie

(define esponente
  (lambda (lista num risultato)
    (if (= (length lista) 0)
        risultato
        (if (= (car lista) num)
            (esponente (cdr lista) num (add1 risultato))
            (esponente (cdr lista) num risultato)
            )
        )
    ))

(define primo?
  (lambda (n)
    (if (= (remainder n 2) 0)
        (= n 2)
        (not (ci-sono-divisori-dispari-di? n 3))
        )
    ))







;funzioni fatte in classe

(define remainder-rec
  (lambda (n x)
    (if (= (remainder n x) 0)
        x
        (remainder-rec n (+ x 1))
        )
    ))


(define ci-sono-divisori-dispari-di?
  (lambda (n k)
    (cond ((> (* k k) n) false)
          ((= (remainder n k) 0) true)
          (else (ci-sono-divisori-dispari-di? n (+ k 2))))
    ))
                         
(define ci-sono-divisori-di?
  (lambda (n k)
    (if (= (remainder n k) 0)
        #t
        (ci-sono-divisori-di? n (+ k 1))
        )
    ))

(define mcm
  (λ (m n)
    (mcm-rec m n 1)
    ))

(define mcm-rec
  (λ (m n k)
    (if (= (remainder (* k m) n) 0)
        (* k m)
        (mcm-rec m n (+ k 1))
        )
    ))

(define intervallo
  (lambda (n)
    (if (< n 1)
        null
        (append (intervallo (- n 1)) (list n))
        )
    ))

(define intervallo-rec
  (lambda (inf n)
    (if (> inf n)
        null
        (cons inf (intervallo-rec (+ inf 1) n))
        )
    ))