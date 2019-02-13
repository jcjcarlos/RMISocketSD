#include<stdio.h>
#include<stdlib.h>
#include<time.h>

typedef struct Account{
    int id;
    char *name;
    double balance;
} Account;

int numAccounts = 0;
char names[3][8] = {"Fulano", "Cicrano", "Beltrano"};

Account *accounts;

void inicializeBank(){
    accounts = (struct Account*) malloc(sizeof(struct Account));
}

Account* findAccountById(int id){
    for(int i=0; i<numAccounts;i++)
        if(accounts[i].id == id)
            return &accounts[i];
    return NULL;
}

Account* createAccount(int id){
    srand(time(NULL));
    Account *newAccount = (Account *) malloc(sizeof(Account));;
    newAccount->id = id;
    newAccount->name = names[rand()%3];
    newAccount->balance = 0.0;
    return newAccount;
}

int addAccount(Account *account){
        accounts[numAccounts].id = account->id;
        accounts[numAccounts].name = account->name;
        accounts[numAccounts].balance = account->balance;
        printf("\nId: %d",accounts[numAccounts].id);
        printf("\nName: %s",accounts[numAccounts].name);
        printf("\nSaldo: %f",accounts[numAccounts].balance);
        printf("\n");
        return account->id;
}

int changeAccount(Account *account, double value){
    if((account->balance + value) < 0.0)
        return 0;
    account->balance += value;
    return 1;
}

int getIdFromUser(){
    int id = 0;
    printf("Digite o id da conta: ");
    scanf("%d", &id);
    printf("\n");
    return id;
}

double getValueFromUser(){
    double value = 0;
    printf("Digite o valor: ");
    scanf("%lf", &value);
    printf("\n");
    return value;
}

void listAccounts(){
    for (int i = 0; i < numAccounts; i++){
        printf("\nId: %d",accounts[i].id);
        printf("\nName: %s",accounts[i].name);
        printf("\nSaldo: %f",accounts[i].balance);
        printf("\n");
    }
}

void showMenu(){
    printf("Selecione a opcao desejada:\n");
    printf("\t1 - Criar Conta\n");
    printf("\t2 - Depositar em Conta\n");
    printf("\t3 - Sacar\n");
    printf("\t4 - Ver Saldo\n");
}

void testPercorrerVetor(){
    for (int i = 0; i<50; i++){
        printf("Criando conta: %d\n", addAccount(createAccount(i)));
        srand(time(NULL));
    }
    listAccounts();
}

int main(){
    inicializeBank();
    testPercorrerVetor();/*
    showMenu();
    do{
        int opc =0;
        scanf("%d", &opc);
    switch(opc){
        case 0:
            printf("Encerrando a aplicacao\n");
            return 0;
        case 1:
            addAccount(createAccount(getIdFromUser()));
            break;
        case 2:
            break;
        
    }
    }while(opc);
    
    //createAccount();
    listAccounts();*/
    
}


