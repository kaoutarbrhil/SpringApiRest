package com.api.springapirest.controller;

import com.api.springapirest.dto.request.EmployeeCreateDTO;
import com.api.springapirest.dto.request.EmployeeUpdateDTO;
import com.api.springapirest.dto.response.EmployeeResponseDTO;
import com.api.springapirest.exception.NotFoundEmployeeException;
import com.api.springapirest.service.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("employee_controller")
@CrossOrigin("http://localhost:3000/*")
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController implements IEmployeeController{

    @Autowired
    @Qualifier("employee_service")
    private final IEmployeeService employeeService;

    @Operation(summary = "Récupérer tous les employés", description = "Retourne une liste de tous les employés")
    @ApiResponse(responseCode = "200", description = "Liste des employés récupérée avec succès")
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees(
            @Parameter(description = "Numéro de page")
            @RequestParam(defaultValue = "1") int numPage,
            @Parameter(description = "Nombre par page")
            @RequestParam(defaultValue = "10") int limite
    ) {
        return ResponseEntity.ok(
                employeeService.getEmployees(numPage, limite)
        );
    }

    @Operation(summary = "Récupérer un employé par ID", description = "Retourne les détails d'un employé spécifique")
    @ApiResponse(responseCode = "200", description = "Employé trouvé")
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(
            @Parameter(description = "ID d'employé", required = true)
            @PathVariable Long id) throws NotFoundEmployeeException {
        return ResponseEntity.status(HttpStatus.OK).body(
                employeeService.getEmployeeById(id)
        );
    }

    @Operation(summary = "Ajouter un nouvel employé", description = "Crée un nouvel employé avec les informations fournies")
    @ApiResponse(responseCode = "201", description = "Employé créé avec succès")
    @PostMapping
    public ResponseEntity<?> addNewEmployee(@Valid @RequestBody EmployeeCreateDTO employeeCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                employeeService.saveEmployee(employeeCreateDTO)
        );
    }

    @Operation(summary = "Supprimer un employé", description = "Supprime un employé avec l'ID spécifié")
    @ApiResponse(responseCode = "204", description = "Employé supprimé avec succès")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeEmployeeById(
            @Parameter(description = "ID d'employé", required = true)
            @PathVariable Long id) throws NotFoundEmployeeException {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Mettre à jour un employé", description = "Met à jour les informations d'un employé")
    @ApiResponse(responseCode = "200", description = "Employé mis à jour avec succès")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(
            @Parameter(description = "ID d'employé", required = true)
            @PathVariable Long id, @Valid @RequestBody EmployeeUpdateDTO updatedEmployee) throws NotFoundEmployeeException {
        return ResponseEntity.ok(
                employeeService.updateEmployee(id, updatedEmployee)
        );
    }

    @Operation(summary = "Login employee", description = "Authenticate employee with email and password")
    @ApiResponse(responseCode = "200", description = "Login successful")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        return ResponseEntity.ok(employeeService.login(email, password));
    }

    @Operation(summary = "Logout employee", description = "Logs out the current employee")
    @ApiResponse(responseCode = "204", description = "Logout successful")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String email) {
        employeeService.logout(email);
        return ResponseEntity.noContent().build();
    }

}
