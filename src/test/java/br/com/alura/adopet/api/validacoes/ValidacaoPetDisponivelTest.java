package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {

    @InjectMocks
    private ValidacaoPetDisponivel validacao;

    @Mock
    private PetRepository petRepository;

    @Mock
    private Pet pet;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    @DisplayName("Deve permitir a solicitação de adoção de pet")
    void permitirAdocao(){


        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        //não lançar erro
        BDDMockito.given(pet.getAdotado()).willReturn(false);

        //Se uma exceção não é chamada
        Assertions.assertDoesNotThrow(() -> validacao.validar(dto));

    }
    @Test
    @DisplayName("Não deve permitir a solicitação de adoção de pet")
    void naoDevepermitirAdocao(){


        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        //lançar erro
        BDDMockito.given(pet.getAdotado()).willReturn(true);

        //Se uma exceção não é chamada
        Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(dto));

    }
}
