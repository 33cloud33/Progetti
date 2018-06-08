;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname hanoi) (read-case-sensitive #t) (teachpacks ((lib "Teachpack_hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "Teachpack_hanoi.ss" "installed-teachpacks")) #f)))
; n=numero di dischi
; k=numero mossa

(define hanoi-disks
  (lambda (n k)
    (let ((lista-mosse (hanoi-moves n))     ;lista contenente tutte le mosse che vengono effettuate
          (num-mosse (- (expt 2 n) 1)))     ;il numero totale di mosse per spostare l'intera torre
      (if (<= k num-mosse)
          (hanoi-picture (towers-background n) (calcolo 0 k lista-mosse n 0 0
                                                        (build-list n (lambda (x) (+ x 1))) null null) n 0 1)
          "la mossa non esiste"  
      ))
    ))

(define hanoi-picture
  (lambda (picture lista n t p)
    ; d = dimensione disco -> (car (car lista))
    ; n = numero dischi -> dall'esterno
    ; p = asticella -> 1, 2, 3
    ; t = altezza -> (length (cdr lista))
    
          (if (or (null? lista) (null? (car lista)))
                  ; passaggio da un'asta alla successiva
                  (cond ((< p 3) (hanoi-picture picture
                                                (cdr lista)        ; (list (list 2 1) (list 3 1))
                                                n
                                                0
                                                (add1 p)))
                        ((= p 3) picture))
              
                  ; carica un disco sopra al precedente
                  (cond ((= p 1) (hanoi-picture (above (disk-image (caar lista) n 1 t) picture)              ;es. (list (list 1 2 3 4 5) (list 6 7) (list 8 9) -> (caar lista) -> 1
                                                (list (cdr (first lista)) (second lista) (third lista))
                                                n
                                                (add1 t)
                                                1
                                                ))
                        ((= p 2) (hanoi-picture (above (disk-image (caar lista) n 2 t) picture)
                                                (list (cdr (first lista)) (second lista))
                                                n
                                                (add1 t)
                                                2
                                                ))
                        ((= p 3) (hanoi-picture (above (disk-image (caar lista) n 3 t) picture)  ; (list (list 3 1))
                                                (list (cdr (first lista)))
                                                n
                                                (add1 t)
                                                3
                                                ))
                        )
                  )
              ))

  (define calcolo
    (lambda (mossa-iniziale mossa-finale lista-mosse asta1 asta2 asta3 lista1 lista2 lista3)  
      (if (= mossa-iniziale mossa-finale)
          ;risultato
          (list (reverse lista1) (reverse lista2) (reverse lista3))        
          ;ricorsione
          (let ((nuova-mossa (add1 mossa-iniziale))
                (nuova-lista (cdr lista-mosse)))
            ;disco spostato da asta1
            (cond ((= (caar lista-mosse) 1)
                   (cond ((= (cadar lista-mosse) 2)
                          (calcolo nuova-mossa mossa-finale nuova-lista
                                   (sub1 asta1) (add1 asta2) asta3
                                   (cdr lista1) (cons (car lista1) lista2) lista3)  ; lista1 -> lista2
                          )
                         ((= (cadar lista-mosse) 3)
                          (calcolo nuova-mossa mossa-finale nuova-lista
                                   (sub1 asta1) asta2 (add1 asta3)
                                   (cdr lista1) lista2 (cons (car lista1) lista3))   ; lista1 -> lista3
                          )
                         ))
                  ;disco spostato da asta2
                  ((= (caar lista-mosse) 2)
                   (cond ((= (cadar lista-mosse) 1)
                          (calcolo nuova-mossa mossa-finale nuova-lista
                                   (add1 asta1) (sub1 asta2) asta3
                                   (cons (car lista2) lista1) (cdr lista2) lista3)  ; lista2 -> lista1
                          )
                         ((= (cadar lista-mosse) 3)
                          (calcolo nuova-mossa mossa-finale nuova-lista
                                   asta1 (sub1 asta2) (add1 asta3)
                                   lista1 (cdr lista2) (cons (car lista2) lista3))   ; lista2 -> lista3
                          )
                         ))
                  ;disco spostato da asta3
                  ((= (caar lista-mosse) 3)
                   (cond ((= (cadar lista-mosse) 1)
                          (calcolo nuova-mossa mossa-finale nuova-lista
                                   (add1 asta1) asta2 (sub1 asta3)
                                   (cons (car lista3) lista1) lista2 (cdr lista3)   ; lista3 -> lista1
                                   ))
                         ((= (cadar lista-mosse) 2)
                          (calcolo nuova-mossa mossa-finale nuova-lista
                                   asta1 (add1 asta2) (sub1 asta3)
                                   lista1 (cons (car lista3) lista2) (cdr lista3)   ; lista3 -> lista2
                                   ))
                         ))
                  )))
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