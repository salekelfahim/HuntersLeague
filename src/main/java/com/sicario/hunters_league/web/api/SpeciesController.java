package com.sicario.hunters_league.web.api;

import com.sicario.hunters_league.domain.Enum.SpeciesType;
import com.sicario.hunters_league.domain.Species;
import com.sicario.hunters_league.service.SpeciesService;
import com.sicario.hunters_league.web.vm.mapper.SpeciesMapper;
import com.sicario.hunters_league.web.vm.requestVM.SpeciesSaveVM;
import com.sicario.hunters_league.web.vm.requestVM.SpeciesUpdateVM;
import com.sicario.hunters_league.web.vm.responseVM.SpeciesVM;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/species")
public class SpeciesController {
    private final SpeciesService speciesService;
    private final SpeciesMapper speciesMapper;

    public SpeciesController(SpeciesService speciesService, SpeciesMapper speciesMapper) {
        this.speciesService = speciesService;
        this.speciesMapper = speciesMapper;
    }

    @PostMapping
    public SpeciesSaveVM create(@Valid @RequestBody SpeciesSaveVM speciesSaveVM) {
        Species species = speciesMapper.speciesSaveVMToSpecies(speciesSaveVM);
        Species savedSpecies = speciesService.save(species);
        return speciesMapper.speciesToSpeciesSaveVM(savedSpecies);
    }

    @PutMapping
    public SpeciesUpdateVM update(@Valid @RequestBody SpeciesUpdateVM speciesUpdateVM) {
        Species species = speciesMapper.speciesUpdateVMToSpecies(speciesUpdateVM);
        Species updatedSpecies = speciesService.update(species);
        return speciesMapper.speciesToSpeciesUpdateVM(updatedSpecies);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        speciesService.delete(id);
        return ResponseEntity.ok("Species deleted successfully.");
    }


    @GetMapping
    public ResponseEntity<Page<SpeciesVM>> filterByType(
            @RequestParam(name = "page", required = false, defaultValue = "${pagination.defaultPage}") int page,
            @RequestParam(name = "size", required = false, defaultValue = "${pagination.defaultPageSize}") int size,
            @RequestParam(name = "category", required = false) Optional<SpeciesType> category) {
        Page<Species> species = speciesService
                .filterByType(page, size, category);
        Page<SpeciesVM> speciesVMPage = species.map(speciesMapper::speciesToSpeciesVM);
        return ResponseEntity.ok(speciesVMPage);
    }


}
