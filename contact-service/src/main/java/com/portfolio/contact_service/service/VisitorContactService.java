package com.portfolio.contact_service.service;

import com.portfolio.contact_service.entity.Action;
import com.portfolio.contact_service.entity.Visitor;
import com.portfolio.contact_service.entity.VisitorContact;
import com.portfolio.contact_service.enums.ActionTypes;
import com.portfolio.contact_service.model.ContactRequestDTO;
import com.portfolio.contact_service.model.ResponseDTO;
import com.portfolio.contact_service.model.VisitorRequestDTO;
import com.portfolio.contact_service.repository.ActionsRepo;
import com.portfolio.contact_service.repository.VisitorContactsRepo;
import com.portfolio.contact_service.repository.VisitorsRepo;
import com.portfolio.contact_service.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VisitorContactService {

    @Autowired
    private ActionsRepo actionsRepo;

    @Autowired
    private VisitorsRepo visitorsRepo;

    @Autowired
    private VisitorContactsRepo visitorContactsRepo;

    @Autowired
    private PortfolioMailService portfolioMailService;

    public List<Visitor> getVisitors(String actionType) {
        Optional<Action> mayBeAction = actionsRepo.findByActionType(actionType);
        List<Visitor> visitors = visitorsRepo.findAll();
        if (mayBeAction.isPresent()) {
            visitors = visitors.stream().filter(visitor -> visitor.getAction().equals(mayBeAction.get())).toList();
        }
        return visitors;
    }

    public ResponseDTO recordPortfolioView(VisitorRequestDTO visitorRequestDTO) {
        if (StringUtils.isValidEmail(visitorRequestDTO.getEmail())) {
            Optional<Action> mayBeAction = actionsRepo.findByActionType(ActionTypes.VIEW_PORTFOLIO.getValue());
            if (mayBeAction.isPresent()) {
                Optional<Visitor> mayBeVisitor = visitorsRepo.findByEmailAndAction(visitorRequestDTO.getEmail(), mayBeAction.get());
                if (mayBeVisitor.isPresent()) {
                    Visitor visitor = mayBeVisitor.get();
                    visitor.setUpdatedAt(LocalDateTime.now());
                    visitorsRepo.save(visitor);
                    portfolioMailService.sendPortfolioViewEmail(visitorRequestDTO.getEmail());
                    return new ResponseDTO("success", "Hello friend, Good to see you again!");
                } else {
                    Visitor visitor = new Visitor(visitorRequestDTO.getEmail(), mayBeAction.get());
                    visitorsRepo.save(visitor);
                    portfolioMailService.sendPortfolioViewEmail(visitorRequestDTO.getEmail());
                    return new ResponseDTO("success", "Welcome to my portfolio!");
                }
            } else {
                return new ResponseDTO("error", "View portfolio action not found");
            }
        } else {
            return new ResponseDTO("error", "MailId is invalid");
        }
    }

    public ResponseDTO recordDownloadResume(VisitorRequestDTO visitorRequestDTO) {
        if (StringUtils.isValidEmail(visitorRequestDTO.getEmail())) {
            Optional<Action> mayBeAction = actionsRepo.findByActionType(ActionTypes.DOWNLOAD_RESUME.getValue());
            if (mayBeAction.isPresent()) {
                Optional<Visitor> mayBeVisitor = visitorsRepo.findByEmailAndAction(visitorRequestDTO.getEmail(), mayBeAction.get());
                if (mayBeVisitor.isPresent()) {
                    Visitor visitor = mayBeVisitor.get();
                    visitor.setUpdatedAt(LocalDateTime.now());
                    visitorsRepo.save(visitor);
                    portfolioMailService.sendDownloadResumeEmail(visitorRequestDTO.getEmail());
                    return new ResponseDTO("success", "Hello friend, Good to see you again!");
                } else {
                    Visitor visitor = new Visitor(visitorRequestDTO.getEmail(), mayBeAction.get());
                    visitorsRepo.save(visitor);
                    portfolioMailService.sendDownloadResumeEmail(visitorRequestDTO.getEmail());
                    return new ResponseDTO("success", "Thanks for having interest in my Resume!");
                }
            } else {
                return new ResponseDTO("error", "Download resume action not found");
            }
        } else {
            return new ResponseDTO("error", "MailId is invalid");
        }
    }

    public ResponseDTO handleVisitorContact(ContactRequestDTO contactRequestDTO) {
        if (StringUtils.isValidEmail(contactRequestDTO.getEmail())) {
            Optional<Action> mayBeAction = actionsRepo.findByActionType(ActionTypes.EMAIL_CONTACT.getValue());
            if (mayBeAction.isPresent()) {
                Optional<Visitor> mayBeVisitor = visitorsRepo.findByEmailAndAction(contactRequestDTO.getEmail(), mayBeAction.get());
                Visitor visitor;
                if (mayBeVisitor.isPresent()) {
                    visitor = mayBeVisitor.get();
                    visitor.setUpdatedAt(LocalDateTime.now());
                } else {
                    visitor = new Visitor(contactRequestDTO.getEmail(), mayBeAction.get());
                }
                visitorsRepo.save(visitor);
                VisitorContact visitorContact = new VisitorContact(visitor, contactRequestDTO.getSubject(), contactRequestDTO.getBody());
                visitorContactsRepo.save(visitorContact);
                portfolioMailService.sendVisitorContactEmail(contactRequestDTO.getEmail(), contactRequestDTO.getSubject(), contactRequestDTO.getBody());
                return new ResponseDTO("success", "Thanks for contacting, I will get back to you soon!");
            } else {
                return new ResponseDTO("error", "Edit contact action not found");
            }
        } else {
            return new ResponseDTO("error", "MailId is invalid");
        }
    }
}
