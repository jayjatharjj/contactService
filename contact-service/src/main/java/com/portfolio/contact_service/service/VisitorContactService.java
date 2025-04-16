package com.portfolio.contact_service.service;

import com.portfolio.contact_service.entity.Action;
import com.portfolio.contact_service.entity.Visitor;
import com.portfolio.contact_service.enums.ActionTypes;
import com.portfolio.contact_service.model.ResponseDTO;
import com.portfolio.contact_service.model.VisitorRequestDTO;
import com.portfolio.contact_service.repository.ActionsRepo;
import com.portfolio.contact_service.repository.VisitorContactsRepo;
import com.portfolio.contact_service.repository.VisitorsRepo;
import com.portfolio.contact_service.utils.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisitorContactService {

    @Autowired
    ActionsRepo actionsRepo;

    @Autowired
    VisitorsRepo visitorsRepo;

    @Autowired
    VisitorContactsRepo visitorContactsRepo;

    public ResponseDTO recordPortfolioView(VisitorRequestDTO visitorRequestDTO) {
        if (StringUtils.isValidEmail(visitorRequestDTO.getEmail())) {
            Optional<Action> mayBeAction = actionsRepo.findByActionType(ActionTypes.VIEW_PORTFOLIO.getValue());
            if (mayBeAction.isPresent()) {
                Visitor visitor = new Visitor(visitorRequestDTO.getEmail(), mayBeAction.get());
                visitorsRepo.save(visitor);
                return new ResponseDTO("success", "Visitor recorded");
            } else {
                return new ResponseDTO("error", "View portfolio action not found");
            }
        } else {
            return new ResponseDTO("error", "MailId is invalid");
        }
    }
}
