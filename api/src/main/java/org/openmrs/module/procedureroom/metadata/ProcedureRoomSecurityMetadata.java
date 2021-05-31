package org.openmrs.module.procedureroom.metadata;

import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.openmrs.module.metadatadeploy.bundle.Requires;
import org.springframework.stereotype.Component;

import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.idSet;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.privilege;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.role;

/**
 * Implementation of access control to the app.
 */
@Component
@Requires(org.openmrs.module.kenyaemr.metadata.SecurityMetadata.class)
public class ProcedureRoomSecurityMetadata extends AbstractMetadataBundle {
	
	public static class _Privilege {
		
		public static final String APP_PROCEDURE_ROOM_APP = "App: procedureroom.procedure";
	}
	
	public static final class _Role {
		
		public static final String APPLICATION_PROCEDURE_ROOM_MODULE = "EHR Procedure Room";
	}
	
	/**
	 * @see AbstractMetadataBundle#install()
	 */
	@Override
	public void install() {
		install(privilege(_Privilege.APP_PROCEDURE_ROOM_APP, "Able to access Key EHR procedure room  module features"));
		install(role(_Role.APPLICATION_PROCEDURE_ROOM_MODULE, "Can access EHR procedure room module Application",
		    idSet(org.openmrs.module.kenyaemr.metadata.SecurityMetadata._Role.API_PRIVILEGES_VIEW_AND_EDIT),
		    idSet(_Privilege.APP_PROCEDURE_ROOM_APP)));
	}
}
