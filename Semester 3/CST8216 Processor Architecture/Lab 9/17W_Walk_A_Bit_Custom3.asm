; 17W_Walk_A_Bit_Custom3.asm
#include C:\68HCS12\registers.inc

; Author:       Thien An Dao, Brady McIntosh
; Course:       CST8216 Fall 2015
; S/N:          040902993, 040706980
; Date:         7 Nov 2018

; Purpose       To walk a bit on Dragon 12+ Trainer

; Program Constants
STACK   equ     $2000
DVALUE  equ     #180

        org     $1000   ; data area


TABLE        db      %00000000, %00010001, %00100010, %01000100, %10001000
ENDTABLE

        org     $2000           ; program code
Start   lds     #STACK          ; stack location

; Configure the Ports
        jsr     Config_SWs_and_LEDs

; Walking through LED
Begin   ldx     #TABLE       	; load table into X acc
Forw    ldaa    1,x+            ; load next value
        staa    portb           ; load into motherboard
        ldaa    #DVALUE         ; delay value
        jsr     Delay_ms        ; delay by value in A acc
        cpx     #ENDTABLE       ; check if at end of table
        bne     Forw            ; continues loop
Back    ldaa    1,x-            ; load previous value
        staa    portb           ; load into motherboard
        ldaa    #DVALUE         ; delay value
        jsr     Delay_ms        ; delay by value in A acc
        cpx     #TABLE       	; check if at beginning of table
        bne     Back            ; continues loop
        bra     Begin           ; restarts looop

; Library Files

#include C:\68HCS12\Lib\Config_SWs_and_LEDs.asm
#include C:\68HCS12\Lib\Delay_ms.asm
        end