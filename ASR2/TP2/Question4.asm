    .data 0x10000000
tab: .word 10, 20, 30, 40
tab1: .word 1, 2, 3, 4
indice: .word 2

    .text
    .globl __start
    
__start:

        li $t0, 0x1111
        li $t1, 0x2222
        li $t2, 0x3333
        
        la $a0 tab
        lw $a1 indice
        jal change # change(tab, indice)
        
        la $a0 tab1
        jal change
        
        li $v0 10
        syscall # syscall 10 (exit)
        
        change:
        
        
            addi $sp,$sp,-12 #sauvegarde des registres en pile
            sw $t0, 0($sp)
            sw $t1, 4($sp)
            sw $t2, 8($sp)
            
            mul $t0,$a1,4
            add $t0,$a0,$t0
            lw $t1,0($t0)
            lw $t2,4($t0)
            sw $t1,4($t0)
            sw $t2,0($t0)
            
            lw $t0, 0($sp)
            lw $t1, 4($sp)
            lw $t2, 8($sp)
            addi $sp,$sp,12
            
            jr $31      #retour de la procédure