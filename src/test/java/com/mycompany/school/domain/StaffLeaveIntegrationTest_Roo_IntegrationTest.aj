// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.domain;

import com.mycompany.school.domain.StaffLeave;
import com.mycompany.school.domain.StaffLeaveDataOnDemand;
import com.mycompany.school.domain.StaffLeaveIntegrationTest;
import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StaffLeaveIntegrationTest_Roo_IntegrationTest {
    
    declare @type: StaffLeaveIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: StaffLeaveIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: StaffLeaveIntegrationTest: @Transactional;
    
    @Autowired
    StaffLeaveDataOnDemand StaffLeaveIntegrationTest.dod;
    
    @Test
    public void StaffLeaveIntegrationTest.testCountStaffLeaves() {
        Assert.assertNotNull("Data on demand for 'StaffLeave' failed to initialize correctly", dod.getRandomStaffLeave());
        long count = StaffLeave.countStaffLeaves();
        Assert.assertTrue("Counter for 'StaffLeave' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void StaffLeaveIntegrationTest.testFindStaffLeave() {
        StaffLeave obj = dod.getRandomStaffLeave();
        Assert.assertNotNull("Data on demand for 'StaffLeave' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'StaffLeave' failed to provide an identifier", id);
        obj = StaffLeave.findStaffLeave(id);
        Assert.assertNotNull("Find method for 'StaffLeave' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'StaffLeave' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void StaffLeaveIntegrationTest.testFindAllStaffLeaves() {
        Assert.assertNotNull("Data on demand for 'StaffLeave' failed to initialize correctly", dod.getRandomStaffLeave());
        long count = StaffLeave.countStaffLeaves();
        Assert.assertTrue("Too expensive to perform a find all test for 'StaffLeave', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<StaffLeave> result = StaffLeave.findAllStaffLeaves();
        Assert.assertNotNull("Find all method for 'StaffLeave' illegally returned null", result);
        Assert.assertTrue("Find all method for 'StaffLeave' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void StaffLeaveIntegrationTest.testFindStaffLeaveEntries() {
        Assert.assertNotNull("Data on demand for 'StaffLeave' failed to initialize correctly", dod.getRandomStaffLeave());
        long count = StaffLeave.countStaffLeaves();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<StaffLeave> result = StaffLeave.findStaffLeaveEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'StaffLeave' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'StaffLeave' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void StaffLeaveIntegrationTest.testFlush() {
        StaffLeave obj = dod.getRandomStaffLeave();
        Assert.assertNotNull("Data on demand for 'StaffLeave' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'StaffLeave' failed to provide an identifier", id);
        obj = StaffLeave.findStaffLeave(id);
        Assert.assertNotNull("Find method for 'StaffLeave' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStaffLeave(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'StaffLeave' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void StaffLeaveIntegrationTest.testMergeUpdate() {
        StaffLeave obj = dod.getRandomStaffLeave();
        Assert.assertNotNull("Data on demand for 'StaffLeave' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'StaffLeave' failed to provide an identifier", id);
        obj = StaffLeave.findStaffLeave(id);
        boolean modified =  dod.modifyStaffLeave(obj);
        Integer currentVersion = obj.getVersion();
        StaffLeave merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'StaffLeave' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void StaffLeaveIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'StaffLeave' failed to initialize correctly", dod.getRandomStaffLeave());
        StaffLeave obj = dod.getNewTransientStaffLeave(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'StaffLeave' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'StaffLeave' identifier to be null", obj.getId());
        try {
            obj.persist();
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        obj.flush();
        Assert.assertNotNull("Expected 'StaffLeave' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void StaffLeaveIntegrationTest.testRemove() {
        StaffLeave obj = dod.getRandomStaffLeave();
        Assert.assertNotNull("Data on demand for 'StaffLeave' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'StaffLeave' failed to provide an identifier", id);
        obj = StaffLeave.findStaffLeave(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'StaffLeave' with identifier '" + id + "'", StaffLeave.findStaffLeave(id));
    }
    
}
