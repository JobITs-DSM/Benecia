package com.jobits.dsm.benecia.domain.enterprise.presentation.payload.response;
import com.jobits.dsm.benecia.domain.enterprise.code.BusinessAreaCode;
import com.jobits.dsm.benecia.domain.enterprise.code.EnterpriseEmployeeCountCode;
import com.jobits.dsm.benecia.domain.enterprise.domain.Director;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EnterpriseInfoResponse {

    private final String registrationNumber;
    private final String name;
    private final String establishYear;
    private final String representativeName;
    private final String address;
    private final String introduction;
    private final EnterpriseEmployeeCountCode employeeCount;
    private final String site;
    private final Integer turnover;
    private final Director director;
    private final BusinessAreaCode businessAreas;
    private final AttachmentDetails businessLicense;
    private final AttachmentDetails logo;
    private final AttachmentDetails material;
    private final AttachmentDetails foreground;

    @Getter
    @AllArgsConstructor
    public static class AttachmentDetails {
        private final Integer id;
        private final String fileName;
        private final String originalFileName;
    }
}
