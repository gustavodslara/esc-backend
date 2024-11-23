package com.github.gustavodslara.esc_service.application.adapter;

import com.github.gustavodslara.esc_service.application.port.GerarPdfUseCase;
import com.github.gustavodslara.esc_service.domain.entity.MatrizResponsabilidade;
import com.github.gustavodslara.esc_service.domain.entity.Os;
import com.github.gustavodslara.esc_service.domain.entity.TermoRecisao;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;

@Service
public class GerarPdfUseCaseImpl implements GerarPdfUseCase {

    @Override
    public byte[] gerarPdfOs(Os os) {
        try (InputStream reportStream = getClass().getResourceAsStream("/esc-os.jrxml");
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("IdOs", os.id.toString());

            parameters.put("Ano", String.valueOf(2024));
            parameters.put("UnidadeS", os.unidadeSolicitante);
            parameters.put("AcaoEdu", os.tituloEvento);


            for (int i = 0; i < os.datas.size(); i++) {
                LocalDate date = LocalDate.parse(os.datas.get(i));
                String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                parameters.put("Data" + (i + 1), formattedDate);

                LocalTime hora = LocalTime.parse(os.horas.get(i));
                String formattedHora = hora.format(DateTimeFormatter.ofPattern("HH:mm"));
                parameters.put("Hora" + (i + 1), formattedHora);

                LocalTime horafim = LocalTime.parse(os.horasfim.get(i));
                String formattedHorafim = horafim.format(DateTimeFormatter.ofPattern("HH:mm"));
                parameters.put("Horafim" + (i + 1), formattedHorafim);
            }

            for (int i = 0; i < os.coordenacaoApoioAcao.size(); i++) {
                parameters.put("CoordAcao" + (i + 1), os.coordenacaoApoioAcao.get(i));
            }

            for (int i = 0; i < os.coordenacaoApoioOperacional.size(); i++) {
                parameters.put("CoordOp" + (i + 1), os.coordenacaoApoioOperacional.get(i));
            }


            parameters.put("QtdPart", os.quantidadeParticipantes.toString());
            parameters.put("CargaHr", os.cargaHoraria.toString());
            parameters.put("Modalidade", os.modalidade.toString());
            parameters.put("Demanda", os.demanda.getDescricao().toString());
            parameters.put("CoordGeral", os.coordenadorGeral);
            parameters.put("CoordInst", os.coordenadorApoioInstitucional);
            parameters.put("CoordCap", os.coordenadorAcaoCapacitacao);
            if (os.equipeTecnica.size() > 1) {
                parameters.put("EquipeTec", String.join(", ", os.equipeTecnica));
            } else {
                parameters.put("EquipeTec", os.equipeTecnica.get(0));
            }
            parameters.put("EquipeJud", os.equipeJuridica);
            parameters.put("Observacao", os.observacao);
            parameters.put("OsPor", os.osElaboradaPor);
            parameters.put("Local", os.local);
            parameters.put("PublicoAlvo", os.publicoAlvo);

            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            return outputStream.toByteArray();

        } catch (JRException | IOException e) {
            System.err.println(e.getMessage());
            return new byte[0];
        }
    }

    @Override
    public byte[] gerarPdfMr(MatrizResponsabilidade mr) {
        try (InputStream reportStream = getClass().getResourceAsStream("/esc-mr.jrxml");
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            Map<String, Object> parameters = new HashMap<>();

            // --- Properties from Os (inherited by MatrizResponsabilidade) ---
            parameters.put("IdOs", mr.id.toString());
            parameters.put("Ano", String.valueOf(2024));
            parameters.put("UnidadeS", mr.unidadeSolicitante);
            parameters.put("AcaoEdu", mr.tituloEvento);

            for (int i = 0; i < mr.datas.size(); i++) {
                LocalDate date = LocalDate.parse(mr.datas.get(i));
                String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                parameters.put("Data" + (i + 1), formattedDate);

                LocalTime hora = LocalTime.parse(mr.horas.get(i));
                String formattedHora = hora.format(DateTimeFormatter.ofPattern("HH:mm"));
                parameters.put("Hora" + (i + 1), formattedHora);

                LocalTime horafim = LocalTime.parse(mr.horasfim.get(i));
                String formattedHorafim = horafim.format(DateTimeFormatter.ofPattern("HH:mm"));
                parameters.put("Horafim" + (i + 1), formattedHorafim);
            }

            for (int i = 0; i < mr.coordenacaoApoioAcao.size(); i++) {
                parameters.put("CoordAcao" + (i + 1), mr.coordenacaoApoioAcao.get(i));
            }

            for (int i = 0; i < mr.coordenacaoApoioOperacional.size(); i++) {
                parameters.put("CoordOp" + (i + 1), mr.coordenacaoApoioOperacional.get(i));
            }

            parameters.put("QtdPart", mr.quantidadeParticipantes.toString());
            parameters.put("CargaHr", mr.cargaHoraria.toString());
            parameters.put("Modalidade", mr.modalidade.toString());
            parameters.put("Demanda", mr.demanda.getDescricao().toString());
            parameters.put("CoordGeral", mr.coordenadorGeral);
            parameters.put("CoordInst", mr.coordenadorApoioInstitucional);
            parameters.put("CoordCap", mr.coordenadorAcaoCapacitacao);
            if (mr.equipeTecnica.size() > 1) {
                parameters.put("EquipeTec", String.join(", ", mr.equipeTecnica));
            } else {
                parameters.put("EquipeTec", mr.equipeTecnica.get(0));
            }
            parameters.put("EquipeJud", mr.equipeJuridica);
            parameters.put("Observacao", mr.observacao);
            parameters.put("OsPor", mr.osElaboradaPor);
            parameters.put("Local", mr.local);
            parameters.put("PublicoAlvo", mr.publicoAlvo);


            // --- Properties specific to MatrizResponsabilidade ---

            parameters.put("EquipeEdu", mr.equipeEdu);
            parameters.put("Tema", mr.tituloEvento);
            parameters.put("Objetivo", mr.objetivo);


            parameters.put("nome_presidente1", mr.nome_presidente1);
            parameters.put("atribuicoes1", mr.atribuicoes1);
            parameters.put("nome_presidente2", mr.nome_presidente2);
            parameters.put("atribuicoes2", mr.atribuicoes2);
            parameters.put("nome_presidente3", mr.nome_presidente3);
            parameters.put("atribuicoes3", mr.atribuicoes3);
            parameters.put("nome_presidente4", mr.nome_presidente4);
            parameters.put("atribuicoes4", mr.atribuicoes4);
            parameters.put("nome_presidente5", mr.nome_presidente5);
            parameters.put("atribuicoes5", mr.atribuicoes5);
            parameters.put("nome_presidente6", mr.nome_presidente6);
            parameters.put("atribuicoes6", mr.atribuicoes6);
            parameters.put("nome_presidente7", mr.nome_presidente7);
            parameters.put("atribuicoes7", mr.atribuicoes7);
            parameters.put("nome_presidente8", mr.nome_presidente8);
            parameters.put("atribuicoes8", mr.atribuicoes8);
            parameters.put("nome_presidente9", mr.nome_presidente9);
            parameters.put("atribuicoes9", mr.atribuicoes9);

            parameters.put("atribuioces9", mr.atribuioces9);

            parameters.put("nome_presidente10", mr.nome_presidente10);
            parameters.put("atribuicoes10", mr.atribuicoes10);
            parameters.put("nome_presidente11", mr.nome_presidente11);
            parameters.put("atribuicoes11", mr.atribuicoes11);
            parameters.put("nome_presidente12", mr.nome_presidente12);
            parameters.put("atribuicoes12", mr.atribuicoes12);
            parameters.put("nome_presidente13", mr.nome_presidente13);
            parameters.put("atribuicoes13", mr.atribuicoes13);
            parameters.put("nome_presidente14", mr.nome_presidente14);
            parameters.put("atribuicoes14", mr.atribuicoes14);
            parameters.put("nome_presidente15", mr.nome_presidente15);
            parameters.put("atribuicoes15", mr.atribuicoes15);
            parameters.put("nome_presidente16", mr.nome_presidente16);
            parameters.put("atribuicoes16", mr.atribuicoes16);
            parameters.put("nome_presidente17", mr.nome_presidente17);
            parameters.put("atribuicoes17", mr.atribuicoes17);
            parameters.put("nome_presidente18", mr.nome_presidente18);
            parameters.put("atribuicoes18", mr.atribuicoes18);
            parameters.put("nome_presidente19", mr.nome_presidente19);
            parameters.put("atribuicoes19", mr.atribuicoes19);
            parameters.put("nome_presidente20", mr.nome_presidente20);
            parameters.put("atribuicoes20", mr.atribuicoes20);
            parameters.put("nome_presidente21", mr.nome_presidente21);
            parameters.put("atribuicoes21", mr.atribuicoes21);
            parameters.put("nome_presidente22", mr.nome_presidente22);
            parameters.put("atribuicoes22", mr.atribuicoes22);
            parameters.put("nome_presidente23", mr.nome_presidente23);
            parameters.put("atribuicoes23", mr.atribuicoes23);
            parameters.put("nome_presidente24", mr.nome_presidente24);
            parameters.put("atribuicoes24", mr.atribuicoes24);
            parameters.put("nome_presidente25", mr.nome_presidente25);
            parameters.put("atribuicoes25", mr.atribuicoes25);
            parameters.put("nome_presidente26", mr.nome_presidente26);
            parameters.put("atribuicoes26", mr.atribuicoes26);

            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(jasperPrint,
                    outputStream);

            return outputStream.toByteArray();

        } catch (JRException | IOException
        e) {
            System.err.println(e.getMessage());
            return new byte[0];
        }
    }


    @Override
    public byte[] gerarPdfTr(TermoRecisao tr) {
        try (InputStream reportStream = getClass().getResourceAsStream("/esc-tr.jrxml");
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("IdOs", tr.id.toString());
            parameters.put("Ano", String.valueOf(2024));
            parameters.put("Esc", tr.esc);
            parameters.put("CategoriaInvest", tr.categoria);
            parameters.put("Modalidade", tr.modalidade.toString());
            parameters.put("Anexo", tr.anexo);
            parameters.put("Objeto", tr.objeto);
            parameters.put("ContratacaoJust", tr.justificativa);
            parameters.put("ModalidadeLicit", tr.modalidadeLicitacao);
            parameters.put("DoInvestimento", tr.investimento);
            parameters.put("ValoresEstimados", tr.especificacoes);
            parameters.put("Ementa", tr.ementa);
            parameters.put("ObrigacoesContratante", tr.obrigacoesContratante);
            parameters.put("ObrigacoesContratado", tr.obrigacoesContratada);
            parameters.put("Sancoes", tr.sancoes);
            parameters.put("Dotacao", tr.dotacaoOrcamentaria);
            parameters.put("Pagamento", tr.condicoesPagamento);
            parameters.put("Documentos", tr.documentosRegularidade);
            parameters.put("NomeAssinante", tr.nomeAssinante);
            parameters.put("Contrato", tr.contrato);
            parameters.put("CargoAssinante", tr.cargoAssinante);
            parameters.put("Acompanhamento", tr.acompanhamento);


            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            System.out.println(tr.toString());
            return outputStream.toByteArray();
        } catch (JRException | IOException e) {
            System.err.println(e.getMessage());
            return new byte[0];
        }
    }
}
