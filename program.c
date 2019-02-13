#include<program.h>
int numAccounts = 0;
//char names[3][8] = {"Fulano", "Cicrano", "Beltrano"};

Account accounts[10];
//Account *accounts;


void inicializeBank(){
    //accounts = (Account*) malloc(sizeof(struct Account));
    for (int i = 0; i < 10; i++){
        accounts[i].id = 0;
        accounts[i].balance = 0.0;
    }
}


int findAccountById(int id){
    for(int i=0; i<numAccounts;i++)
        if(accounts[i].id == id)
            return i;
    return -1;
}

/*
Account* findAccountById(int id){
    for(int i=0; i<numAccounts;i++)
        if(accounts[i].id == id)
            return &accounts[i];
    return NULL;
}
*/

/*
Account* createAccount(int id){
    srand(time(NULL));
    Account *newAccount = (Account *) malloc(sizeof(Account));
    newAccount->id = id;
    newAccount->name = names[rand()%3];
    newAccount->balance = 0.0;
    numAccounts++;
    return newAccount;
}
*/

int createAccount(int id){
    //srand(time(NULL));
    accounts[numAccounts].id = id;
    //newAccount.name = names[rand()%3];
    numAccounts++;
    
    return 1;
}

/*
int addAccount(Account *account){
        accounts[numAccounts].id = account->id;
        //accounts[numAccounts].name = account->name;
        accounts[numAccounts].balance = account->balance;
        
        printf("\nId: %d",accounts[numAccounts].id);
        printf("\nName: %s",accounts[numAccounts].name);
        printf("\nSaldo: %f",accounts[numAccounts].balance);
        printf("\n");
//         
        return account->id;
}

*/

/*
int changeAccount(Account *account, double value){
    if((account->balance + value) < 0.0)
        return 0;
    account->balance += value;
    return 1;
}
*/

int changeAccount(int id, double value){
    int accountNumberFound = findAccountById(id);
    if((accounts[accountNumberFound].balance + value) < 0.0 || accounts[accountNumberFound].id == 0)
        return 0;
    accounts[accountNumberFound].balance += value;
    return 1;
}

int getIdFromUser(){
    int id = 0;
    printf("Digite o id da conta: ");
    scanf("%d", &id);
    return id;
}

double getValueFromUser(){
    double value = 0;
    printf("Digite o valor: ");
    scanf("%lf", &value);
    return value;
}

int listAccount(int id){
    int accountNumberFound = findAccountById(id);
    if(accountNumberFound == -1)
        return 0;
    printf("\tInformacoes da Conta:\n");
    printf("ID: %d\n", accounts[accountNumberFound].id);
    printf("Saldo: %lf\n", accounts[accountNumberFound].balance);
    return 1;
}

void listAccounts(){
    for (int i = 0; i < numAccounts; i++){
        printf("\nId: %d",accounts[i].id);
        //printf("\nName: %s",accounts[i].name);
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

void testCriarEListarContas(){
    for (int i = 0, j = 3; i<10; i++, j=j+3){
        createAccount(j);
        //srand(time(NULL));
    }
    listAccounts();
}

void testBuscarConta(){
    //Account *testAccount = findAccountById(1);
    int accountNumberFound = findAccountById(12);
    if(accountNumberFound != -1)
        printf("accounts[%d].id == %d\n", accountNumberFound ,accounts[accountNumberFound].id);
}

int main(){
    inicializeBank();
    //testCriarEListarContas();
    //testBuscarConta();/*
    int opc = 0;
    int result = 1;
    do{
        showMenu();
        scanf("%d", &opc);
    switch(opc){
        case 0:
            printf("Encerrando a aplicacao\n");
            return 0;
        case 1:
            result = createAccount(getIdFromUser());
            break;
        case 2:
            result = changeAccount(getIdFromUser(), getValueFromUser());
            break;
        case 3:
            result = changeAccount(getIdFromUser(), -getValueFromUser());
            break;
        case 4:
            result = listAccount(getIdFromUser());
            break;
    }
    if(result)
        printf("Operacao realizada com sucesso!\n");
    else
	printf("Nao foi possivel realizar a operacao\n");
    }while(opc);
    
    //createAccount();
    //listAccounts();
    
}


