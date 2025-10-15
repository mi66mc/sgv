package presentation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import model.Aeronave;
import model.Aeroporto;
import model.CompanhiaAerea;
import model.FlightStatus;
import model.Passageiro;
import model.Piloto;
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
        limparBancoDeDados();
        
        System.out.println("--- Testes SGV ---");

        testeCompanhiaAereaDAO();
        testeAeronaveDAO();
        testeAeroportoDAO();
        testePilotoDAO();
        testePassageiroDAO();
        testeRotaDAO();
        testeVooDAO();

        System.out.println("--- Fim dos Testes ---");
    }

    private static void limparBancoDeDados() {
        System.out.println("\n--- Limpando o banco de dados ---");
        try {
            new ReservaDAO().deleteAll();
            new VooDAO().deleteAll();
            new RotaAeroportoDAO().deleteAll();
            new GerenteDAO().deleteAll();
            new ConfiguracaoDeCompanhiaDAO().deleteAll();
            new AeronaveDAO().deleteAll();
            new AeroportoDAO().deleteAll();
            new RotaDAO().deleteAll();
            new PilotoDAO().deleteAll();
            new PassageiroDAO().deleteAll();
            new CompanhiaAereaDAO().deleteAll();
            System.out.println("--- Banco de dados limpo ---");
        } catch (SQLException e) {
            System.err.println("Erro ao limpar o banco de dados.");
            e.printStackTrace();
        }
    }

    private static void testeCompanhiaAereaDAO() {
        System.out.println("\n--- Testando CompanhiaAereaDAO ---");
        CompanhiaAereaDAO dao = new CompanhiaAereaDAO();
        CompanhiaAerea model = new CompanhiaAerea();
        model.setNome("Test Air");
        model.setNacionalidade("Brazilian");
        model.setEmail("test@air.com");
        model.setSenha("123456");

        try {
            CompanhiaAerea created = dao.create(model);
            System.out.println("Companhia Aerea criada com sucesso! ID: " + created.getId());

            CompanhiaAerea found = dao.findById(created.getId());
            System.out.println("Buscando por id " + created.getId() + ": " + found.getNome());
            
            found.setNome("Updated Test Air");
            dao.update(found);
            System.out.println("Companhia " + found.getId() + " atualizada.");
            
            System.out.println("Deletando companhia " + found.getId());
            dao.delete(found.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testeAeronaveDAO() {
        System.out.println("\n--- Testando AeronaveDAO ---");
        AeronaveDAO dao = new AeronaveDAO();
        CompanhiaAereaDAO companhiaDAO = new CompanhiaAereaDAO();
        
        CompanhiaAerea companhia = new CompanhiaAerea();
        companhia.setNome("Parent Company");
        companhia.setNacionalidade("International");
        companhia.setEmail("parent@company.com");
        companhia.setSenha("parentpass");
        
        try {
            CompanhiaAerea createdCompanhia = companhiaDAO.create(companhia);

            Aeronave model = new Aeronave();
            model.setNome("Test Plane");
            model.setPeso(5000.0);
            model.setQtdAssentosTotal(150);
            model.setCompanhiaId(createdCompanhia.getId());

            Aeronave createdAeronave = dao.create(model);
            System.out.println("Aeronave criada com sucesso! ID: " + createdAeronave.getId());

            Aeronave found = dao.findById(createdAeronave.getId());
            System.out.println("Buscando por id " + found.getId() + ": " + found.getNome());
            
            found.setNome("Updated Test Plane");
            dao.update(found);
            System.out.println("Aeronave " + found.getId() + " atualizada.");
            
            System.out.println("Deletando aeronave " + found.getId());
            dao.delete(found.getId());
            
            System.out.println("Deletando companhia " + createdCompanhia.getId());
            companhiaDAO.delete(createdCompanhia.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testeAeroportoDAO() {
        System.out.println("\n--- Testando AeroportoDAO ---");
        AeroportoDAO dao = new AeroportoDAO();
        CompanhiaAereaDAO companhiaDAO = new CompanhiaAereaDAO();

        CompanhiaAerea companhia = new CompanhiaAerea();
        companhia.setNome("Airport Owner");
        companhia.setNacionalidade("National");
        companhia.setEmail("owner@airport.com");
        companhia.setSenha("ownerpass");

        try {
            CompanhiaAerea createdCompanhia = companhiaDAO.create(companhia);

            Aeroporto model = new Aeroporto();
            model.setNome("Test Airport");
            model.setLocalizacao("Test City");
            model.setCompanhiaId(createdCompanhia.getId());

            Aeroporto createdAeroporto = dao.create(model);
            System.out.println("Aeroporto criado com sucesso! ID: " + createdAeroporto.getId());

            Aeroporto found = dao.findById(createdAeroporto.getId());
            System.out.println("Buscando por id " + found.getId() + ": " + found.getNome());
            
            found.setNome("Updated Test Airport");
            dao.update(found);
            System.out.println("Aeroporto " + found.getId() + " atualizado.");

            System.out.println("Deletando aeroporto " + found.getId());
            dao.delete(found.getId());

            System.out.println("Deletando companhia " + createdCompanhia.getId());
            companhiaDAO.delete(createdCompanhia.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void testePilotoDAO() {
        System.out.println("\n--- Testando PilotoDAO ---");
        PilotoDAO dao = new PilotoDAO();
        Piloto model = new Piloto();
        model.setNome("Test Pilot");
        model.setCpf("123.456.789-00");
        model.setEmail("pilot@test.com");
        model.setSenha("pilotpass");

        try {
            Piloto created = dao.create(model);
            System.out.println("Piloto criado com sucesso! ID: " + created.getId());

            Piloto found = dao.findById(created.getId());
            System.out.println("Buscando por id " + found.getId() + ": " + found.getNome());
            
            found.setNome("Updated Test Pilot");
            dao.update(found);
            System.out.println("Piloto " + found.getId() + " atualizado.");
            
            System.out.println("Deletando piloto " + found.getId());
            dao.delete(found.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testePassageiroDAO() {
        System.out.println("\n--- Testando PassageiroDAO ---");
        PassageiroDAO dao = new PassageiroDAO();
        Passageiro model = new Passageiro();
        model.setNome("Test Passenger");
        model.setCpf("000.111.222-33");
        model.setEmail("passenger@test.com");
        model.setSenha("passengerpass");

        try {
            Passageiro created = dao.create(model);
            System.out.println("Passageiro criado com sucesso! ID: " + created.getId());

            Passageiro found = dao.findById(created.getId());
            System.out.println("Buscando por id " + found.getId() + ": " + found.getNome());
            
            found.setNome("Updated Test Passenger");
            dao.update(found);
            System.out.println("Passageiro " + found.getId() + " atualizado.");

            System.out.println("Deletando passageiro " + found.getId());
            dao.delete(found.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testeRotaDAO() {
        System.out.println("\n--- Testando RotaDAO ---");
        RotaDAO dao = new RotaDAO();
        Rota model = new Rota();
        model.setNome("Test Route");

        try {
            Rota created = dao.create(model);
            System.out.println("Rota criada com sucesso! ID: " + created.getId());

            Rota found = dao.findById(created.getId());
            System.out.println("Buscando por id " + found.getId() + ": " + found.getNome());
            
            found.setNome("Updated Test Route");
            dao.update(found);
            System.out.println("Rota " + found.getId() + " atualizada.");

            System.out.println("Deletando rota " + found.getId());
            dao.delete(found.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testeVooDAO() {
        System.out.println("\n--- Testando VooDAO ---");
        VooDAO vooDAO = new VooDAO();
        CompanhiaAereaDAO companhiaDAO = new CompanhiaAereaDAO();
        AeronaveDAO aeronaveDAO = new AeronaveDAO();
        PilotoDAO pilotoDAO = new PilotoDAO();
        RotaDAO rotaDAO = new RotaDAO();

        try {
            CompanhiaAerea companhia = new CompanhiaAerea();
            companhia.setNome("Voo Company");
            companhia.setNacionalidade("Voo Nation");
            companhia.setEmail("voo@company.com");
            companhia.setSenha("voopass");
            CompanhiaAerea createdCompanhia = companhiaDAO.create(companhia);

            Aeronave aeronave = new Aeronave();
            aeronave.setNome("Voo Plane");
            aeronave.setPeso(6000.0);
            aeronave.setQtdAssentosTotal(200);
            aeronave.setCompanhiaId(createdCompanhia.getId());
            Aeronave createdAeronave = aeronaveDAO.create(aeronave);

            Piloto piloto = new Piloto();
            piloto.setNome("Voo Pilot");
            piloto.setCpf("987.654.321-00");
            piloto.setEmail("voo.pilot@test.com");
            piloto.setSenha("pilotpass");
            Piloto createdPiloto = pilotoDAO.create(piloto);

            Rota rota = new Rota();
            rota.setNome("Voo Route");
            Rota createdRota = rotaDAO.create(rota);

            System.out.println("Created companhia ID: " + createdCompanhia.getId());
            System.out.println("Created aeronave ID: " + createdAeronave.getId() + " linked to companhia ID: " + createdAeronave.getCompanhiaId());

            Voo voo = new Voo();
            voo.setData(LocalDate.now());
            voo.setHora(LocalTime.now());
            voo.setStatus(FlightStatus.AGENDADO);
            voo.setQtdAssentos(100);
            voo.setCompanhiaId(createdCompanhia.getId());
            voo.setAeronaveId(createdAeronave.getId());
            voo.setPilotoId(createdPiloto.getId());
            voo.setRotaId(createdRota.getId());
            Voo createdVoo = vooDAO.create(voo);
            System.out.println("Voo criado com sucesso! ID: " + createdVoo.getId());

            Voo foundVoo = vooDAO.findById(createdVoo.getId());
            System.out.println("Buscando por id " + foundVoo.getId() + ": Voo em " + foundVoo.getData());
            
            foundVoo.setStatus(FlightStatus.CONCLUIDO);
            vooDAO.update(foundVoo);
            System.out.println("Voo " + foundVoo.getId() + " atualizado.");

            System.out.println("Deletando voo " + foundVoo.getId());
            vooDAO.delete(foundVoo.getId());

            // Cleanup
            System.out.println("Deletando dependencias do voo...");
            System.out.println("Deleting aeronave ID: " + createdAeronave.getId());
            aeronaveDAO.delete(createdAeronave.getId());
            System.out.println("Deleting companhia ID: " + createdCompanhia.getId());
            companhiaDAO.delete(createdCompanhia.getId());
            pilotoDAO.delete(createdPiloto.getId());
            rotaDAO.delete(createdRota.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}