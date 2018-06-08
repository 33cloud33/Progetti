;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Esercizio 1 - frasi|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define frase
  (lambda (nome ver compl)
    (string-append (articolo-nome nome) " " (verbo nome ver) " " (articolo-nome compl)) 
    ))          

(define ultimo-carattere
  (lambda (str)    ;prende l'ultimo carattere
    (string-ref str (- (string-length str) 1))
    ))

(define ultimi-tre
  (lambda (str)          ;prende gli ultimi tre caratteri (per il verbo)
    (substring str (- (string-length str) 3))
    ))

(define plurale
  (lambda (str)
    (cond ((or (char=? (ultimo-carattere str) #\e) (char=? (ultimo-carattere str) #\i)) "plurale")
          (else "singolare")
          )
    ))

(define m-f
  (lambda (str)
    (cond ((or (char=? (ultimo-carattere str) #\a) (char=? (ultimo-carattere str) #\e)) "femminile")
          (else "maschile")
          )
    ))

(define verbo
  (lambda (nome ver)
    (if (string=? (plurale nome) "plurale")
        (cond ((string=? (ultimi-tre ver) "are")
               (string-append (substring ver 0 (- (string-length ver) 3)) "ano")) ;cacciare -> cacciano
              ((string=? (ultimi-tre ver) "ere")
               (string-append (substring ver 0 (- (string-length ver) 3)) "ono")) ;leggere -> leggono
              ((string=? (ultimi-tre ver) "ire")
               (string-append (substring ver 0 (- (string-length ver) 3)) "ono")) ;dormire -> dormono
              )
        (cond ((string=? (ultimi-tre ver) "are")
               (substring ver 0 (- (string-length ver) 2))) ;cacciare -> caccia
              ((string=? (ultimi-tre ver) "ere")
               (substring ver 0 (- (string-length ver) 2))) ;leggere -> legge
              ((string=? (ultimi-tre ver) "ire")
               (string-append (substring ver (- (string-length ver) 3)) "e")) ;dormire -> dorme
              )
        )
    ))

(define articolo-nome
  (lambda (nome)
    (cond ((and (string=? (plurale nome) "singolare") (string=? (m-f nome) "maschile")) ;se maschile singolare
           (string-append "il " nome))
          ((and (string=? (plurale nome) "plurale") (string=? (m-f nome) "maschile")) ;se maschile plurale
           (string-append "i " nome))
          ((and (string=? (plurale nome) "singolare") (string=? (m-f nome) "femminile")) ;se femminile singolare
           (string-append "la " nome))
          ((and (string=? (plurale nome) "plurale") (string=? (m-f nome) "femminile")) ;se femminile plurale
           (string-append "le " nome))
          )
    ))