; Those_Were_The_Memories.asm
;

Value1  equ     64

        org     $1000
Value2  db      $2B
Value3  dw      5678


        org     $1020
Result1 ds      1
Result2 ds      2
Result3 ds      1

        org     $2000
        ldaa    #Value1
        staa    Result3
        ldab    Value2
        stab    Result1
        ldd     #Value3
        stab    Result3
        staa    Result2
        ldab    Value2
        ldaa    Value2+1
        std     Result1
        swi
        end