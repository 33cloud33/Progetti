;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Esercizio_N6) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
;; L-Tessellation Problem
;;
;; Questo problema si basa su tasselli a forma di L, con
;; i lati lunghi di 2 unita' e quelli corti di 1 unita'.
;; La forma di ogni tassello, nella configurazione di
;; riferimento, e' associata al nome
;;
;;   L-tile
;;
;; Hai a disposizione tanti tasselli quanti ne servono e li
;; puoi spostare e combinare fra loro con le procedure:
;;
;;   (shift-down <figura> <passi>)
;;
;;   (shift-right <figura> <passi>)
;;
;;   (quarter-turn-right <figura>)
;;
;;   (quarter-turn-left <figura>)
;;
;;   (half-turn <figura>)
;;
;;   (glue-tiles <figura> <figura>)
;;
;; L'argomento <figura> e' nel caso piu' semplice un tassello e in
;; generale una figura ottenuta spostando, ruotando e combinando
;; diversi tasselli. L'argomento <passi> rappresenta il numero di
;; unita' di spostamento in basso o a destra.
;;
;; Le operazioni consentono, rispettivamente, di spostae in basso,
;; spostare a destra, ruotare di 90 gradi in senso orario, ruotare
;; di 90 gradi in senso antiorario, capovolgere e combinare
;; insieme due figure: si capisce facilmente provando, per esempio:
;;
;;   (glue-tiles L-tile (shift-right (half-turn L-tile) 1))
;;
;; Immagina che i tasselli rappresntino piastrelle con le quali
;; si deve coprire (o piastrellare) il pavimento di una stanza,
;; ma senza tagliare (o, peggio, sovrapporre) piastrelle.
;; Il problema e' risolubile se il pavimento della stanza e'
;; un quadrato di lato n unita', dove n e' una potenza di due,
;; da cui e' stata tolta una colonna quadrata di lato 1 unita'
;; in corrispondenza al vertice in basso a destra.
;; L'obiettivo e' di definire un'opportuna procedura in Scheme
;; "per piastrellare il pavimento" (naturalmente, se la forma e
;; le dimensioni sono consistenti con le specifiche date).


;; Traslazione unitaria da utilizzare per la tassellazione
;; (lato piu' corto del tassello a forma di L)

(set-tessellation-shift-step!)

(define L-tessellation
  (lambda (num)
   (cond ((= num 1) L-tile)  ;controlla se num = 1      
         (else;altrimenti
          (glue-tiles 
                (glue-tiles
                 (L-tessellation (/ num 2));ripete la stessa diviso 2
                 (shift-right (quarter-turn-right (L-tessellation (/ num 2))) num);shifta e ruota di un quarto a destra
                )
                (glue-tiles
                 (shift-down (quarter-turn-left (L-tessellation (/ num 2)) )  num);shifta in basso e ruota di un quarto a sinistra
                 (shift-down (shift-right (L-tessellation (/ num 2))(/ num 2))(/ num 2)) ;shifta in basso e shifta a destra
                )
     
            )
          )
         )
    ))

