;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname btr-sum) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define btr-sum
  (lambda (x y)
    (cond ((string=? x "") y)
          ((string=? y "") x)
          (else (btr-carry-sum x y #\. '()))
          )
    ))

; lsd = funzione che si occupa di prendere la cifra meno significativa (l'ultima)
;     input  = un intero (stringa)
;     output = if (stringa vuota)   #\.
;              else                 ultimo carattere della stringa
(define lsd
  (lambda (str)
    (if (empty? str)
        #\.
        (string-ref str (- (string-length str) 1))
        )
    ))

; head = funzione che prende tutta la stringa escludendo l'ultimo carattere
;     input  = un intero (stringa)
;     output = if (stringa vuota)   ""
;              else                 stringa fino a penultima posizione
(define head
  (lambda (str)
    (if (empty? str)
        ""
        (substring str 0 (- (string-length str) 1))
        )
    ))

;btr-carry-sum = fa la somma tra due cifre in colonna e il relativo riporto
;     input  = due stringhe (str1, str2) e riporto (car1)
;     output = somma delle due stringhe + riporto
(define btr-carry-sum
  (lambda (str1 str2 car1 lista)
    (cond ((empty? str1) str2)
          ((empty? str2) str1)
          ((and (= (string-length str1) 0) (= (string-length str2) 0))        
           (normalized-btr (list->string (append (list car1) lista))))
          ((= (string-length str1) (string-length str2))
           (btr-carry-sum (head str1)
                          (head str2)
                          (btr-carry (lsd str1) (lsd str2) car1)
                          (append (list (btr-digit-sum (lsd str1) (lsd str2) car1)) lista))) ;OK
          ((> (string-length str1) (string-length str2))
           (btr-carry-sum (head str1)
                          (head (string-append (make-string (- (string-length str1) (string-length str2)) #\.) str2))
                          (btr-carry (lsd str1) (lsd str2) car1)
                          (append (list (btr-digit-sum (lsd str1) (lsd str2) car1)) lista)))
          ((< (string-length str1) (string-length str2))
           (btr-carry-sum (head (string-append (make-string (- (string-length str2) (string-length str1)) #\.) str1))
                          (head str2)
                          (btr-carry (lsd str1) (lsd str2) car1)
                          (append (list (btr-digit-sum (lsd str1) (lsd str2) car1)) lista)))          
          )
        ))

;btr-carry = calcola SOLO il riporto nella somma delle tre cifre
;     input  = due cifre in colonna (str1, str2) e un riporto (car1)
;     output = il riporto calcolato dalla somma
(define btr-carry
  (lambda (str1 str2 car1)
    (cond ((or (and (char=? str1 #\+) (char=? str2 #\+) (char=? car1 #\+))
               (and (char=? str1 #\+) (char=? str2 #\+) (char=? car1 #\.))
               (and (char=? str1 #\+) (char=? str2 #\.) (char=? car1 #\+))
               (and (char=? str1 #\.) (char=? str2 #\+) (char=? car1 #\+)))
           #\+)
          ((or (and (char=? str1 #\-) (char=? str2 #\-) (char=? car1 #\-))
               (and (char=? str1 #\-) (char=? str2 #\-) (char=? car1 #\.))
               (and (char=? str1 #\-) (char=? str2 #\.) (char=? car1 #\-))
               (and (char=? str1 #\.) (char=? str2 #\-) (char=? car1 #\-)))
           #\-)
          (else #\.)
          )
    ))

;btr-digit-sum = calcola la somma effettiva delle tre cifre
;     input  = due cifre in colonna (u, v) e un riporto (c)
;     output = la somma calcolata direttamente
(define btr-digit-sum
  (lambda (u v c)
    (cond ((char=? u #\-)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)#\.)
                        ((char=? c #\.)#\+)
                        ((char=? c #\+)#\-)))
                 ((char=? v #\.)
                  (cond ((char=? c #\-)#\+)
                        ((char=? c #\.)#\-)
                        ((char=? c #\+)#\.)))
                 ((char=? v #\+) c)))
          ((char=? u #\.)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-) #\+)
                        ((char=? c #\.) #\-)
                        ((char=? c #\+) #\.)))
                 ((char=? v #\.) c)
                 ((char=? v #\+)
                  (cond ((char=? c #\-) #\.)
                        ((char=? c #\.) #\+)
                        ((char=? c #\+) #\-)))))
          ((char=? u #\+)
           (cond ((char=? v #\-) c)
                 ((char=? v #\.)
                  (cond ((char=? c #\-) #\.)
                        ((char=? c #\.) #\+)
                        ((char=? c #\+) #\-)))
                 ((char=? v #\+)
                  (cond ((char=? c #\-) #\+)
                        ((char=? c #\.) #\-)
                        ((char=? c #\+) #\.))))) 
          )))

; normalized-btr = restituisce il risultato senza le cifre ininfluenti (non significative)
;     input  = una stringa con cifre non significative
;     output = la stessa stringa senza le cifre non significative
(define normalized-btr
  (lambda (str)
    (if (char=? (string-ref str 0) #\.)
        (if (= (string-length str) 1)
            "."
            (normalized-btr (substring str 1))
            )
        str
        )
    ))



