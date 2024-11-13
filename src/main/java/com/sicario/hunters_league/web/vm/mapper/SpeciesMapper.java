package com.sicario.hunters_league.web.vm.mapper;

import com.sicario.hunters_league.domain.Species;
import com.sicario.hunters_league.web.vm.requestVM.SpeciesSaveVM;
import com.sicario.hunters_league.web.vm.requestVM.SpeciesUpdateVM;
import com.sicario.hunters_league.web.vm.responseVM.SpeciesVM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpeciesMapper {
    SpeciesMapper Instance = Mappers.getMapper(SpeciesMapper.class);

    SpeciesSaveVM speciesToSpeciesSaveVM(Species species);

    Species speciesSaveVMToSpecies(SpeciesSaveVM speciesSaveVM);

    Species speciesUpdateVMToSpecies(SpeciesUpdateVM speciesUpdateVM);

    SpeciesUpdateVM speciesToSpeciesUpdateVM(Species species);

    SpeciesVM speciesToSpeciesVM(Species species);

    Species speciesVMToSpecies(SpeciesVM speciesVM);


}
