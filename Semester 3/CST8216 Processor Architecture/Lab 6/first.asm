; First.asm
;
; Author:               Brady McIntosh
; Student Number:       040706980
; Date:                 17 Oct 2018
;
; Purpose:              Add the following values: %25 + $37 - $1

        org     $1000           ; Set current location to start in RAM
p:      db      $25             ; First addend is at location p
q:      db      $37             ; Second addend is at location q
r:      ds      1               ; Defining storage for resulting sum

        org     $2000           ; Set current location to start in RAM
        ldaa    p               ; Load value at p into accumulator a
        adda    q               ; Add value at q into accumulator a
        deca                    ; Decrement accumulator a
        staa	r		; Store accumulator a at location r
        end

