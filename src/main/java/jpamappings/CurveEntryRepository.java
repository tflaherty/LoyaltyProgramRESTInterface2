package jpamappings;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created programmatically on Wed Aug 10 17:35:53 EDT 2016
 * Database Version:  Microsoft SQL Server 2005 - 9.00.5069.00 (X64) 
	Aug 22 2012 18:02:46 
	Copyright (c) 1988-2005 Microsoft Corporation
	Developer Edition (64-bit) on Windows NT 6.1 (Build 7601: Service Pack 1)

 */
@RepositoryRestResource(collectionResourceRel = "curveentry", path = "curveentry")
public interface CurveEntryRepository  extends PagingAndSortingRepository<CurveEntry, CurveEntryPK> //, CustomCurveEntryRepository
{
}
