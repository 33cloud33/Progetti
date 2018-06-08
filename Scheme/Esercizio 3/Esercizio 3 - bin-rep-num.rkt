;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname bin-rep-num-punt) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(define bin-rep->number
  (lambda (seq)
    (let ((primo (string-ref seq 0))
          (punto (find-char seq #\.))
          (senza-segno (substring seq 1)))
      (cond
        ;segno meno
        ((char=? primo #\-) 
         (if (= punto -1)
             ; es. -10100110
             (* (calcolo senza-segno) -1)
             ; es. -1010.011
             (* (+ (calcolo (substring seq 1 (find-char seq #\.))) 
                (calcolo-dec (substring seq (find-char seq #\.)))) -1)
             ))
        ;segno più (o nessuno)
        (else
         (if (= punto -1)
             ; es. +10100110
             (calcolo seq)
             ; es. +1010.011
             (+ (calcolo (substring seq 0 (find-char seq #\.)))
                (calcolo-dec (substring seq (find-char seq #\.))))
             ))
        ))
    ))  

(define valore-pos
  (lambda (num pos)
    (* (num (expt 2 pos)))  ;[0,1] * 2^[pos]  
    ))

(define esponente
  (lambda (seq pos)
    (sub1 (- (string-length seq) pos))  ;pos=3 length=5 esponente = 5-3-1 -> 1
    ))

(define esponente-neg
  (lambda (pos)
    (* pos -1)   ;0 -> -1; 1 -> -2; 2 -> -3 ...
    ))

(define 0-1
  (lambda (seq pos)
    (if (char=? (string-ref seq pos) #\1)
        1
        0)
    ))

(define calcolo
  (lambda (seq)
    (let ricorsione ([s 0][pos 0])
       (if (< pos (string-length seq))
           (ricorsione (somma-singola s seq pos) (add1 pos))       
           s
           ))
     ))

(define find-char
  (lambda (seq char)
    (let find ([p 0])
      (if (< p (string-length seq))
          (if (char=? (string-ref seq p) char)
              p
              (find (add1 p)))
          -1)          
      )))

(define calcolo-dec
  (lambda (seq)
    (let ricorsione ([s 0][pos 0])
       (if (< pos (string-length seq))
           (ricorsione (somma-singola-neg s seq pos) (add1 pos))       
           s
           ))
     ))

(define somma-singola-neg
  (lambda (prec seq pos)
    (+ prec (* (0-1 seq pos) (expt 2 (esponente-neg pos))))     ; 0 + 1*2^(-4) = 1/16
    ))
    
(define somma-singola
  (lambda (prec seq pos)
    (+ prec (* (0-1 seq pos) (expt 2 (esponente seq pos))))     ; 0 + 1*2^4 = 16
    ))