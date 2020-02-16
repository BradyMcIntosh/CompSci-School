; Author:       Brady McIntosh
; Course:       CST8216 Fall 2018
; S/N:          040706980
; Date:         05 Dec 2018

; Purpose       Bubble-sort from a text file

        org     $1000
        
;Read in array structure from text file
#include         "Array.txt"
End_Array

Flag    db      $00

; Loop
; flag = 0
; var1 <- get value from array
; var2 <- get second value from array+1
;       if (var1 > var2)
;               swap
;       end if
; increment array index
; if at arrayend & flag = 1
;       loop
; end loop

; **swap function**
; var 1 <-> var 2
; put var1 -> array+1
; put var2 -> array
; flag = 1

        org     $2000
        
Loop1   ldx     #Array                ;load x with table beginning
        ldy     #Array+1        ;load y with table beginning
        ldaa    $00             ;load number 0
        staa    Flag            ;set flag to 0
Loop2   ldaa    0,x             ;load x into A
        ldab    0,y             ;load y into B
        cba                     ;compare A and B
        bhi     Swap            ;if A higher than B, swap
        
Cont    inx                     ;increment X
        iny                     ;increment Y
        cpx     #End_Array-1    ;compare X and end of array
        blo     Loop2           ;if X is lower, branches back to Loop2
        ldaa    Flag            ;load Flag into A
        cmpa    #$01            ;compare A to numerical 1
        beq     Loop1           ;if equal, branch back to Loop1
        bra     Last            ;branch to Last (end)
        
Swap    staa    0,y             ;store A into Y
        stab    0,x             ;store B into X
        ldaa    #$01            ;load A with number 0
        staa    Flag            ;store A in Flag location
        bra     Cont            ;branch back to Cont
        
Last        end


        
        