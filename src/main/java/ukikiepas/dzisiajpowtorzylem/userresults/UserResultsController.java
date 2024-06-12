package ukikiepas.dzisiajpowtorzylem.userresults;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukikiepas.dzisiajpowtorzylem.userresults.models.UserResultDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/vocabulary-set/result")
public class UserResultsController {

    private final UserResultsService userResultsService;

    @PostMapping
    public ResponseEntity<?> saveResults(@RequestBody UserResultDto userResultDto) {
        return userResultsService.saveUserResults(userResultDto);
    }

    @GetMapping("/{dateString}")
    public ResponseEntity<?> getResultsDay(@PathVariable String dateString, HttpServletRequest request) {
        return userResultsService.getResultsDay(dateString, request);
    }

}
