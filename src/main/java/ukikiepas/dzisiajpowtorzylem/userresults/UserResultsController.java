package ukikiepas.dzisiajpowtorzylem.userresults;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ukikiepas.dzisiajpowtorzylem.userresults.models.UserResultDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/vocabulary-set/result")
public class UserResultsController {

    private final UserResultsService userResultsService;

    @PostMapping
    public ResponseEntity<?> saveResults(@RequestBody UserResultDto userResultDto){
        return userResultsService.saveUserResults(userResultDto);
    }


}
