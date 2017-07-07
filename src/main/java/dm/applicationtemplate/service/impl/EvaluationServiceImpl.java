package dm.applicationtemplate.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import dm.applicationtemplate.model.Country;
import dm.applicationtemplate.model.Partner;
import dm.applicationtemplate.service.EvaluationService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by David on 06/07/2017.
 */
@Service
public class EvaluationServiceImpl implements EvaluationService {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<Country> evaluate(List<Partner> partners) {

        Map<String, List<Partner>> partnersByCountryName = partners.stream()
                .collect(Collectors.groupingBy(Partner::getCountry));

        return partnersByCountryName.entrySet().stream()
                .map(entry -> evaluateForCountry(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private Country evaluateForCountry(String countryName, List<Partner> partners) {

        Map<Date, Set<Partner>> partnersByDate = getPartnersByDate(partners);

        Map<Date, Set<Partner>> attendeesByStartDate = new HashMap<>();
        Map<Integer, Collection<Date>> datesByAttendance = new HashMap<>();

        int greatestNumAttendees = 0, numAttendees;
        for (Map.Entry<Date, Set<Partner>> entry : partnersByDate.entrySet()) {
            Date date = entry.getKey();
            Date nextDay = DateUtils.addDays(date, 1);

            Set<Partner> availablePartners = entry.getValue();
            Set<Partner> availablePartnersForNextDay = partnersByDate.getOrDefault(nextDay, Collections.emptySet());

            // availableAttendees should contain only the partners who are able to attend on both the start day and the next day
            Set<Partner> availableAttendees = Sets.intersection(availablePartners, availablePartnersForNextDay);

            numAttendees = availableAttendees.size();
            greatestNumAttendees = Math.max(greatestNumAttendees, numAttendees);

            attendeesByStartDate.put(date, availableAttendees);

            if (datesByAttendance.containsKey(numAttendees)) {
                datesByAttendance.get(numAttendees).add(date);
            } else {
                datesByAttendance.put(numAttendees, Lists.newArrayList(date));
            }
        }

        Optional<Date> optimalStartDate = greatestNumAttendees == 0 ? Optional.empty() :
                datesByAttendance.get(greatestNumAttendees).stream()
                .sorted()
                .findFirst();

        String optimalStartDateAsString = optimalStartDate.map(DATE_FORMAT::format).orElse(null);

        Set<String> attendeeEmails = optimalStartDate.map(attendeesByStartDate::get).orElse(Collections.emptySet())
                .stream()
                .map(Partner::getEmail)
                .collect(Collectors.toSet());

        return new Country(greatestNumAttendees, attendeeEmails, countryName, optimalStartDateAsString);
    }

    private Map<Date, Set<Partner>> getPartnersByDate(List<Partner> partners) {

        Map<Date, Set<Partner>> partnersByDate = new HashMap<>();

        for (Partner partner : partners) {
            for (Date date : partner.getAvailableDates()) {
                if (!partnersByDate.containsKey(date)) {
                    partnersByDate.put(date, Sets.newHashSet(partner));
                } else {
                    partnersByDate.get(date).add(partner);
                }
            }
        }

        return partnersByDate;

    }

}
