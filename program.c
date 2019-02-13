#include<stdio.h>
#include<stdlib.h>

typedef struct Account{
    int id;
    char* name;
    double balance;
} Account;

int numAccounts = 0;
char** names = 

Account *accounts;

void inicializeBank(){
    accounts = (struct Account*) malloc(sizeof(struct Account));
}

void addAccount(struct Account account){
    accounts[numAccounts].id = account.id;
    accounts[numAccounts].name = account.name;
    accounts[numAccounts].balance = account.balance;
    numAccounts++;
    
}

Account createAccount(){
    Account newAccount;
    printf("Digite o id da conta: ");
    scanf("%d", &newAccount.id);
    
    
    
}

void listAccounts(){
    for (int i = 0; i < numAccounts; i++){
        printf("\nId: %d",accounts[i].id);
        printf("\nName: %s",accounts[i].name);
        printf("\nSaldo: %f",accounts[i].balance);
        printf("\n");
    }
}
//void imprimirInt(int);

int main(){
    inicializeBank();
    
    /*struct Account myAccount;
    myAccount.id = 1;
    myAccount.name = "Joao Silva";
    myAccount.balance = 50;
    */
    
    createAccount();
    listAccounts();
    
}


