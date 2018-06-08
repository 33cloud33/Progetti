;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Esercizio 9 - hanoi|) (read-case-sensitive #t) (teachpacks ((lib "Teachpack_hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "Teachpack_hanoi.ss" "installed-teachpacks")) #f)))
; n=numero di dischi
; k=numero mossa

(define hanoi-disks
  (lambda (n k)
    (let ((lista-mosse (hanoi-moves n))     ;lista contenente tutte le mosse che vengono effettuate
          (num-mosse (- (expt 2 n) 1)))     ;il numero totale di mosse per spostare l'intera torre
      (if (<= k num-mosse)
          (before-half 0 k lista-mosse n 0 0)
          "la mossa non esiste"  
      ))
    ))

(define before-half
  (lambda (mossa-iniziale mossa-finale lista-mosse asta1 asta2 asta3)  
    (if (= mossa-iniziale mossa-finale)
        ;risultato
        (list (list 1 asta1) (list 2 asta2) (list 3 asta3))
        
        ;ricorsione
        (let ((nuova-mossa (add1 mossa-iniziale))
              (nuova-lista (cdr lista-mosse)))
                 ;disco spostato da asta1
          (cond ((= (caar lista-mosse) 1)
                 (cond ((= (cadar lista-mosse) 2)
                        (before-half nuova-mossa
                                     mossa-finale
                                     nuova-lista
                                     (sub1 asta1)
                                     (add1 asta2)
                                     asta3
                                     ))
                       ((= (cadar lista-mosse) 3)
                        (before-half nuova-mossa
                                     mossa-finale
                                     nuova-lista
                                     (sub1 asta1)
                                     asta2
                                     (add1 asta3)
                                     ))
                       ))
                ;disco spostato da asta2
                ((= (caar lista-mosse) 2)
                 (cond ((= (cadar lista-mosse) 1)
                        (before-half nuova-mossa
                                     mossa-finale
                                     nuova-lista
                                     (add1 asta1)
                                     (sub1 asta2)
                                     asta3
                                     ))
                       ((= (cadar lista-mosse) 3)
                        (before-half nuova-mossa
                                     mossa-finale
                                     nuova-lista
                                     asta1
                                     (sub1 asta2)
                                     (add1 asta3)
                                     ))
                       ))
                ;disco spostato da asta3
                ((= (caar lista-mosse) 3)
                 (cond ((= (cadar lista-mosse) 1)
                        (before-half nuova-mossa
                                     mossa-finale
                                     nuova-lista
                                     (add1 asta1)
                                     asta2
                                     (sub1 asta3)
                                     ))
                       ((= (cadar lista-mosse) 2)
                        (before-half nuova-mossa
                                     mossa-finale
                                     nuova-lista
                                     asta1
                                     (add1 asta2)
                                     (sub1 asta3)
                                     ))
                       ))
                ))
        )
    ))






(define after-half
  (lambda (mossa-iniziale mossa-finale lista-mosse)
    1
    ))

; prese dal testo dell'esercizio

(define hanoi-moves      ; val: lista di coppie
  (lambda (n)            ; n > 0 intero
    (hanoi-rec n 1 2 3)
    ))
(define hanoi-rec    ; val: lista di coppie
  (lambda (n s d t)  ; n intero, s, d, t: asticelle
    (if (= n 1)
        (list (list s d))
        (let ((m1 (hanoi-rec (- n 1) s t d))
              (m2 (hanoi-rec (- n 1) t d s))
              )
          (append m1 (cons (list s d) m2))
          ))
    ))

