; Author:       Thien An Dao, Brady McIntosh
; Course:       CST8216 Fall 2018
; S/N:          040902993, 040706980
; Date:         21 Nov 2018

; Purpose       To reverse-copy an array

STACK   equ     $2000

        org     $1000
        
Source_Array
        db      $01,$FF,$55,$22,$19,$AA,$12,$FE
End_Source
Destination_Array
        ds      Destination_Array-Source_Array 	;auto-calc array size
End_Destination

        org     $2000   	;start program at $2000
        lds     #STACK  	;load stack with #STACK ($2000)
        ldx     #Source_Array   ;start X at beginning of source array
        ldy     #End_Destination;start Y at end of dest array
        
        dey             ;decrement y once before to break gap
Start   ldaa    1,x+    ;load source[X] into A and inc x
        staa    1,y-    ;store A into dest[Y] and dec y
        cpy     #End_Source     ;check if y has reached past beginning of dest
        bge     Start           ;keep going, if not true
        
        end
