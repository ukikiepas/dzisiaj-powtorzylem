package ukikiepas.dzisiajpowtorzylem.email;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ukikiepas.dzisiajpowtorzylem.user.UserRepository;
import ukikiepas.dzisiajpowtorzylem.user.models.User;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.VocabularySetRepository;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.models.VocabularySet;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCheckService {

    private final UserRepository userRepository;
    private final VocabularySetRepository vocabularySetRepository;
    private final EmailService emailService;

    @Scheduled(cron = "0 0 20 * * *")
    @Transactional
    public void checkReviews() throws MessagingException {
        LocalDate today = LocalDate.now();
        List<User> users = userRepository.findAll();

        for (User user : users) {
            List<VocabularySet> sets = vocabularySetRepository.findAllByCreator(user.getUsername());

            for (VocabularySet set : sets) {
                if ((set.getLastReviewed() == null || set.getLastReviewed().isBefore(today)) && set.getIsActive()) {
                    emailService.sendSetReminderEmail(user.getParentEmail(), "Przypomnienie o powtórce", "TYTUŁ !!!",
                            "Twoje dziecko nie wykonało jeszcze dzisiejszej powtórki zestawu słówek: " + set.getTitle());
                }
            }
        }
    }
}

