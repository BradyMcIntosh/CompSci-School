; Math_Operations.asm
; Author:         Brady McIntosh
; Student Number: 040706980
; Date:           24 Oct 2018
;
; Purpose:        To Gain Experience with Assembly Language instructions
;                 by correcting the code and then stepping through it with
;                 the Dragon 12+ Student version simulator and recording the
;                 values in the registers and memory locations AFTER the
;                 instruction has been executed.
;                 Note that this code is purposely NOT documented.

; Program Constants
STACK   equ     $2000

	org	$1000		; Data starts at $1000
MyArray db      $17, $91
Result  ds      2

        org     $2000           ; Program Code starts at $2000
Start   lds     #STACK          ; Setup the stack
	clrb
        ldaa    #$62
        ldab    #62
        adda    MyArray
        staa    Result
        stab    Result+1
        ldaa    Result+1
        ldab    Result
        incb
        ldaa    #%01010101
        std     Result
        swi
        end