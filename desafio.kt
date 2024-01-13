enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Usuario(val name: String, val id: Int)

data class ConteudoEducacional(val nome: String, val duracao: Int) //ao criar vários objetos  de conteudoEducacional, eles podem ser usados em várias formações


data class Formacao(val nome: String, val dificuldade: Nivel = Nivel.BASICO) { //optei por deixar apenas nome e dificuldade para e deixar as disciplinas como função 
    
    private val alunos = mutableListOf<Usuario>() //aqui estão so dados de alunos criados. Não estarão no grupo de formação até serem adicionados
    
    val disciplinas = mutableListOf<String>() //lista com as disciplinas que farão parte da formação
    
    fun matricular(vararg participantes: Usuario) { //tem a capacidade de receber vários alunos na mesma chamada 
         
        for (user in participantes) {
            
           if(alunos.contains(user)) println("O usuário '${user.name}' já está cadastrado") //se o aluno já estiver na formação
           else {
               println("O usuario '${user.name}' foi cadastrado\n")
               alunos.add(user)
           } 
        }
        
    }

    fun addConteudoEducacional(vararg materias: ConteudoEducacional){ //com os conteúdos criados, essa função permite trazê-los para a formação
        for(mat in materias){ 
        	if(disciplinas.contains(mat.nome))
               println("'${mat.nome}' já está no escopo da Formação '$nome'\n") //caso a matéria já esteja no escopo, ela não é adicionada
            else {
               disciplinas.add(mat.nome)
               println("'${mat.nome}' foi adicionada com sucesso em '$nome'\n")
            }
        }
    }
    
	val nivel = when(dificuldade){ //variável que regula o nível de dificuldade da formação. Caso não esteja inicilamente no objeto, é "BASICO" por padrão
        
		Nivel.BASICO -> "Básico"
        Nivel.INTERMEDIARIO -> "Intermediário"
        Nivel.DIFICIL -> "Difícil"
    }
    
    fun inscritos(): List<String>{ //função que retorna os nomes dos inscritos da Formação
        return alunos.map {it.name}
    }

}


fun main() {
   
    // testes para funcionamento do sistema DIO
    //cadastro Alunos
    val Alfa = Usuario("Yuri Alfa", 123)
    val Beta = Usuario("Lupusregina Beta", 234)
    
    //Cadastro Conteúdos Educacionais
    val introducaoPOO = ConteudoEducacional("Introdução a POO", 3)
    val classes = ConteudoEducacional("Classes na orientação a objetos", 2)

    //Cadastro Formações e sua dificuldade
    val dificuldade = Nivel.DIFICIL
    val POO = Formacao("POO com Kotlin", dificuldade)
 
 	//add alunos e matérias nas Formações (duplicados para mandar mensagem de erro)
 	POO.addConteudoEducacional(classes, introducaoPOO, classes)
    POO.matricular(Alfa, Alfa)
    
    println("\n\nO nivel da formação de ${POO.nome} é ${POO.nivel}.\n\nSeu conjunto de matérias são ${POO.disciplinas}\n\nSeus alunos inscritos são ${POO.inscritos()}\n")
    
}