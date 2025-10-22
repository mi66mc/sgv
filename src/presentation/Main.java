package presentation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;

import model.Aeronave;
import model.Aeroporto;
import model.CompanhiaAerea;
import model.FlightStatus;
import model.Passageiro;
import model.Piloto;
import model.Reserva;
import model.Rota;
import model.Voo;
import persistance.AeronaveDAO;
import persistance.AeroportoDAO;
import persistance.CompanhiaAereaDAO;
import persistance.ConfiguracaoDeCompanhiaDAO;
import persistance.GerenteDAO;
import persistance.PassageiroDAO;
import persistance.PilotoDAO;
import persistance.ReservaDAO;
import persistance.RotaAeroportoDAO;
import persistance.RotaDAO;
import persistance.VooDAO;

public class Main {
    public static void main(String[] args) {
    	System.out.println("========== SGV ===========\n");
    	
    	mainPortal();
    	
    	System.out.println("===== ENCERRANDO =====");
    }

    public static void mainPortal() {
    	Scanner scanner = new Scanner(System.in);
    	
    	boolean shouldLoop = true;
    	
    	while (shouldLoop) {
        	System.out.println("===== Portal Inicial =====");
        	
	    	System.out.println("1. Portal do Passageiro");
	    	System.out.println("2. Portal da Companhia Aérea");
	    	System.out.println("3. Portal do Gerente");
	    	System.out.println("4. Portal do Piloto");
	    	System.out.println("5. Encerrar");
	    	System.out.print("Selecione uma opção: ");
	    	
	    	try {
	    		int choice = scanner.nextInt();
	    		scanner.nextLine();
	    		
	    		if (choice <= 0 || choice >= 6) {
	    			throw new InputMismatchException();
	    		}
	    		
	    		switch (choice) {
	    			case 1: {
	    				passengerPortal(scanner);
	    				break;
	    			}
	    			case 5: {
	    				shouldLoop = false;
	    				break;
	    			}
	    		}
	    	} catch (InputMismatchException e) {
		    	System.out.println("\nPor favor, selecione um número valido entre 1 e 5.\n");
		    	scanner.nextLine();
	    		continue;
	    	}
    	}
    	
    	scanner.close();
    }
    
    public static void passengerPortal(Scanner scanner) {
    	boolean shouldLoop = true;
    	
    	while (shouldLoop) {
        	System.out.println("===== Portal do Passageiro =====");
        	
	    	System.out.println("1. Cadastrar-se");
	    	System.out.println("2. Entrar");
	    	System.out.println("3. Voltar");
	    	System.out.print("Selecione uma opção: ");
	    	
	    	try {
	    		int choice = scanner.nextInt();
	    		scanner.nextLine();
	    		
	    		if (choice <= 0 || choice >= 4) {
	    			throw new InputMismatchException();
	    		}
	    		
	    		PassageiroDAO passengerDAO = new PassageiroDAO();
	    		
	    		switch (choice) {
		    		case 1: {
		    	    	System.out.println("===== Processo de Cadastro =====");
		    	    	
		    	    	System.out.print("Insira seu nome: ");
		    			String name = scanner.nextLine();

		    	    	System.out.print("Insira seu CPF: ");
		    			String cpf = scanner.nextLine();

		    	    	System.out.print("Insira seu E-Mail: ");
		    			String email = scanner.nextLine();

		    	    	System.out.print("Insira uma senha: ");
		    			String password = scanner.nextLine();
		    			
		    			Passageiro passenger = new Passageiro(0, name, cpf, email, password);
		    			
		    			try {
		    				if (passengerDAO.findByEmail(email) != null) {
		    					System.out.println("Email já cadastrado, tente outro.");
		    					break;
		    				} else if (passengerDAO.findByCpf(cpf) != null) {
		    					System.out.println("CPF já cadastrado, tente outro.");
		    					break;
		    				}
							passengerDAO.create(passenger);
							
			    	    	System.out.println("===== Passageiro Cadastrado! =====");
						} catch (SQLException e) {
							e.printStackTrace();
						}
		    			
		    			break;
		    		}
		    		case 2: {
		    			System.out.println("===== Entrar no Portal =====");
		    			
		    	    	System.out.print("Insira seu E-Mail: ");
		    			String email = scanner.nextLine();

		    	    	System.out.print("Insira sua senha: ");
		    			String password = scanner.nextLine();
		    			
		    			try {
							Passageiro passenger = passengerDAO.findByEmail(email);
							
							if (passenger == null) {
								System.out.println("Usuário com email \"" + email + "\" não foi encontrado.");
								break;
							}
							
							if (passenger.getSenha().equals(password)) {
								System.out.println("===== Entrou com Sucesso! =====");
								passengerLoggedInPortal(passenger, scanner);
								break;
							} else {
								System.out.println("Senha incorreta.");
							}
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
		    			
		    			break;
		    		}
	    			case 3: {
	    				shouldLoop = false;
	    				break;
	    			}
	    		}
	    	} catch (InputMismatchException e) {
		    	System.out.println("\nPor favor, selecione um número valido entre 1 e 3.\n");
		    	scanner.nextLine();
	    		continue;
	    	}
    	}
    }
    
    public static void passengerLoggedInPortal(Passageiro passenger, Scanner scanner) {
    	boolean shouldLoop = true;
    	
    	while (shouldLoop) {
        	System.out.println("===== Portal do Passageiro =====");
        	
	    	System.out.println("1. Mostrar Informações");
	    	System.out.println("2. Editar Informações");
	    	System.out.println("3. Ver Voos Disponíveis");
	    	System.out.println("4. Minhas Reservas");
	    	System.out.println("5. Sair");
	    	System.out.print("Selecione uma opção: ");
	    	
	    	try {
	    		int choice = scanner.nextInt();
	    		scanner.nextLine();
	    		
	    		if (choice <= 0 || choice >= 6) {
	    			throw new InputMismatchException();
	    		}

				ReservaDAO reservaDAO = new ReservaDAO();
				RotaDAO rotaDAO = new RotaDAO();

				switch (choice) {
		    		case 1: {
		    			System.out.println("===== Suas Informações =====");
		    			System.out.println("Nome: " + passenger.getNome());
		    			System.out.println("CPF: " + passenger.getCpf());
		    			System.out.println("E-Mail: " + passenger.getEmail());
		    			break;
		    		}
		    		case 2: {
		    			PassageiroDAO passengerDAO = new PassageiroDAO();
						
		    			System.out.println("===== Editar Informações =====");
		    			
		    			System.out.print("Insira seu novo nome (atual: " + passenger.getNome() + "): ");
		    			String name = scanner.nextLine();

		    	    	System.out.print("Insira seu novo CPF (atual: " + passenger.getCpf() + "): ");
		    			String cpf = scanner.nextLine();

		    	    	System.out.print("Insira seu novo E-Mail (atual: " + passenger.getEmail() + "): ");
		    			String email = scanner.nextLine();

		    	    	System.out.print("Insira uma nova senha: ");
		    			String password = scanner.nextLine();

						try {
							if (passengerDAO.findByEmail(email) != null) {
								System.out.println("Email já cadastrado, tente outro.");
								break;
							} else if (passengerDAO.findByCpf(cpf) != null) {
								System.out.println("CPF já cadastrado, tente outro.");
								break;
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
		    			
		    			passenger.setNome(name);
		    			passenger.setCpf(cpf);
		    			passenger.setEmail(email);
		    			passenger.setSenha(password);

		    			try {
							passengerDAO.update(passenger);
			    	    	System.out.println("===== Informações Atualizadas! =====");
						} catch (SQLException e) {
							e.printStackTrace();
						}
		    			break;
		    		}
		    		case 3: {
		    			System.out.println("===== Voos Disponíveis =====");
						VooDAO vooDAO = new VooDAO();
						try {
							List<Voo> voos = vooDAO.findAllByDate(LocalDate.now());
							if (voos.isEmpty()) {
								System.out.println("Nenhum voo disponível.");
								break;
							} else {
								printVoos(voos);

								System.out.print("Deseja reservar algum voo? (s/n): ");

								String reservaChoice = scanner.nextLine();
								if (!reservaChoice.equalsIgnoreCase("s")) {
									break;
								}

								System.out.print("Insira o ID do voo que deseja reservar: ");
								int vooId = scanner.nextInt();
								scanner.nextLine();
								
								Voo selectedVoo = null;
								for (Voo voo : voos) {
									if (voo.getId() == vooId) {
										selectedVoo = voo;
										break;
									}
								}
								
								if (selectedVoo == null) {
									System.out.println("Voo com ID " + vooId + " não encontrado entre os disponíveis.");
									break;
								}

								Reserva reserva = new Reserva(0, LocalDateTime.now(), false, selectedVoo.getId(), selectedVoo, passenger.getId(), passenger);

								reservaDAO.create(reserva);
								
								System.out.println("===== Voo Reservado com Sucesso! =====");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
		    			break;
		    		}
		    		case 4: {
		    			System.out.println("===== Minhas Reservas =====");
						try {
							List<Reserva> reservas = reservaDAO.findByUserId(passenger.getId());
							printReservas(reservas);
						} catch (SQLException e) {
							e.printStackTrace();
						}
		    			break;
		    		}
				}
	    	} catch (InputMismatchException e) {
		    	System.out.println("\nPor favor, selecione um número valido entre 1 e 5.\n");
		    	scanner.nextLine();
	    		continue;
	    	}
    	}
    }

	public static void printVoos(List<Voo> voos) {
		RotaDAO rotaDAO = new RotaDAO();

		System.out.println("================================");
		for (Voo voo : voos) {
			System.out.println("| ID: " + voo.getId());
			Rota rota = null;
			try {
				rota = rotaDAO.findById(voo.getRotaId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("| Rota: " + rota.getNome());
			System.out.println("| Data: " + voo.getData());
			System.out.println("| Hora: " + voo.getHora());
			System.out.println("| Status: " + voo.getStatus());
			System.out.println("| Quantidade de Assentos: " + voo.getQtdAssentos());
			System.out.println("================================");
		}
	}

	public static void printReservas(List<Reserva> reservas) {
		VooDAO vooDao = new VooDAO();
		RotaDAO rotaDAO = new RotaDAO();

		System.out.println("================================");
		for (Reserva reserva : reservas) {
			try {
				Voo voo = vooDao.findById(reserva.getVooId());
				Rota rota = rotaDAO.findById(voo.getRotaId());
				System.out.println("| ID: " + reserva.getId());
				System.out.println("| Reservado Em: " + reserva.getReservadoEm());
				System.out.println("| Rota: " + rota.getNome());
				System.out.println("| Data: " + voo.getData());
				System.out.println("| Hora: " + voo.getHora());
				System.out.println("| Status: " + voo.getStatus());
				System.out.println("| Cancelado: " + (reserva.getCancelado() ? "Sim" : "Não"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}