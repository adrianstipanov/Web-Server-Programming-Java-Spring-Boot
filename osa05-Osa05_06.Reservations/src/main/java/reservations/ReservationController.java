package reservations;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/reservations")
    public String addReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationTo) {
        if (reservationFrom != null && reservationTo != null
                && reservationRepository.findOverlappingReservations(reservationFrom, reservationTo).isEmpty()) {
            Reservation r = new Reservation();
            r.setReservationFrom(reservationFrom);
            r.setReservationTo(reservationTo);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            r.setUser(accountRepository.findByUsername(username));

            reservationRepository.save(r);
        }
        return "redirect:/reservations";

    }

    @GetMapping("/reservations")
    public String home(Model model) {
        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservations";
    }




}
