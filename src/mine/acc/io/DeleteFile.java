package mine.acc.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件或目录的删除
 * <p>
 * 本类主要参考了apache.commons.io.FileUtil.java
 * 
 * @author WangFengwei
 * @time 2012-9-3
 */
public class DeleteFile {

	/**
	 * The Unix separator character.
	 */
	private static final char UNIX_SEPARATOR = '/';

	/**
	 * The Windows separator character.
	 */
	private static final char WINDOWS_SEPARATOR = '\\';

	/**
	 * The system separator character.
	 */
	private static final char SYSTEM_SEPARATOR = File.separatorChar;

	/**
	 * Deletes a directory recursively.
	 * 
	 * @param directory
	 *            directory to delete
	 * @throws IOException
	 *             in case deletion is unsuccessful
	 */
	public static void deleteDirectory(File directory) throws IOException {
		if (!directory.exists()) {
			return;
		}

		if (!isSymlink(directory)) {
			cleanDirectory(directory);
		}

		if (!directory.delete()) {
			String message = "Unable to delete directory " + directory + ".";
			throw new IOException(message);
		}
	}

	/**
	 * Deletes a file, never throwing an exception. If file is a directory,
	 * delete it and all sub-directories.
	 * <p>
	 * The difference between File.delete() and this method are:
	 * <ul>
	 * <li>A directory to be deleted does not have to be empty.</li>
	 * <li>No exceptions are thrown when a file or directory cannot be deleted.</li>
	 * </ul>
	 * 
	 * @param file
	 *            file or directory to delete, can be {@code null}
	 * @return {@code true} if the file or directory was deleted, otherwise
	 *         {@code false}
	 * 
	 * @since 1.4
	 */
	public static boolean deleteQuietly(File file) {
		if (file == null) {
			return false;
		}
		try {
			if (file.isDirectory()) {
				cleanDirectory(file);
			}
		} catch (Exception ignored) {
		}

		try {
			return file.delete();
		} catch (Exception ignored) {
			return false;
		}
	}

	/**
	 * Determines whether the {@code parent} directory contains the
	 * {@code child} element (a file or directory).
	 * <p>
	 * Files are normalized before comparison.
	 * </p>
	 * 
	 * Edge cases:
	 * <ul>
	 * <li>A {@code directory} must not be null: if null, throw
	 * IllegalArgumentException</li>
	 * <li>A {@code directory} must be a directory: if not a directory, throw
	 * IllegalArgumentException</li>
	 * <li>A directory does not contain itself: return false</li>
	 * <li>A null child file is not contained in any parent: return false</li>
	 * </ul>
	 * 
	 * @param directory
	 *            the file to consider as the parent.
	 * @param child
	 *            the file to consider as the child.
	 * @return true is the candidate leaf is under by the specified composite.
	 *         False otherwise.
	 * @throws IOException
	 *             if an IO error occurs while checking the files.
	 * @since 2.2
	 * @see FilenameUtils#directoryContains(String, String)
	 */
	public static boolean directoryContains(final File directory,
			final File child) throws IOException {

		// Fail fast against NullPointerException
		if (directory == null) {
			throw new IllegalArgumentException("Directory must not be null");
		}

		if (!directory.isDirectory()) {
			throw new IllegalArgumentException("Not a directory: " + directory);
		}

		if (child == null) {
			return false;
		}

		if (!directory.exists() || !child.exists()) {
			return false;
		}

		// Canonicalize paths (normalizes relative paths)
		String canonicalParent = directory.getCanonicalPath();
		String canonicalChild = child.getCanonicalPath();

		return directoryContains(canonicalParent, canonicalChild);
	}

	/**
	 * Cleans a directory without deleting it.
	 * 
	 * @param directory
	 *            directory to clean
	 * @throws IOException
	 *             in case cleaning is unsuccessful
	 */
	public static void cleanDirectory(File directory) throws IOException {
		if (!directory.exists()) {
			String message = directory + " does not exist";
			throw new IllegalArgumentException(message);
		}

		if (!directory.isDirectory()) {
			String message = directory + " is not a directory";
			throw new IllegalArgumentException(message);
		}

		File[] files = directory.listFiles();
		if (files == null) { // null if security restricted
			throw new IOException("Failed to list contents of " + directory);
		}

		IOException exception = null;
		for (File file : files) {
			try {
				forceDelete(file);
			} catch (IOException ioe) {
				exception = ioe;
			}
		}

		if (null != exception) {
			throw exception;
		}
	}

	/**
	 * Determines whether the specified file is a Symbolic Link rather than an
	 * actual file.
	 * <p>
	 * Will not return true if there is a Symbolic Link anywhere in the path,
	 * only if the specific file is.
	 * <p>
	 * <b>Note:</b> the current implementation always returns {@code false} if
	 * the system is detected as Windows using
	 * {@link FilenameUtils#isSystemWindows()}
	 * 
	 * @param file
	 *            the file to check
	 * @return true if the file is a Symbolic Link
	 * @throws IOException
	 *             if an IO error occurs while checking the file
	 * @since 2.0
	 */
	public static boolean isSymlink(File file) throws IOException {
		if (file == null) {
			throw new NullPointerException("File must not be null");
		}
		if (isSystemWindows()) {
			return false;
		}
		File fileInCanonicalDir = null;
		if (file.getParent() == null) {
			fileInCanonicalDir = file;
		} else {
			File canonicalDir = file.getParentFile().getCanonicalFile();
			fileInCanonicalDir = new File(canonicalDir, file.getName());
		}

		if (fileInCanonicalDir.getCanonicalFile().equals(
				fileInCanonicalDir.getAbsoluteFile())) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Deletes a file. If file is a directory, delete it and all
	 * sub-directories.
	 * <p>
	 * The difference between File.delete() and this method are:
	 * <ul>
	 * <li>A directory to be deleted does not have to be empty.</li>
	 * <li>You get exceptions when a file or directory cannot be deleted.
	 * (java.io.File methods returns a boolean)</li>
	 * </ul>
	 * 
	 * @param file
	 *            file or directory to delete, must not be {@code null}
	 * @throws NullPointerException
	 *             if the directory is {@code null}
	 * @throws FileNotFoundException
	 *             if the file was not found
	 * @throws IOException
	 *             in case deletion is unsuccessful
	 */
	public static void forceDelete(File file) throws IOException {
		if (file.isDirectory()) {
			deleteDirectory(file);
		} else {
			boolean filePresent = file.exists();
			if (!file.delete()) {
				if (!filePresent) {
					throw new FileNotFoundException("File does not exist: "
							+ file);
				}
				String message = "Unable to delete file: " + file;
				throw new IOException(message);
			} else {
				System.out.println("succeed to delete:\t"
						+ file.getAbsolutePath());
			}
		}
	}

	/**
	 * Compares two strings using the case-sensitivity rule.
	 * <p>
	 * This method mimics {@link String#equals} but takes case-sensitivity into
	 * account.
	 * 
	 * @param str1
	 *            the first string to compare, not null
	 * @param str2
	 *            the second string to compare, not null
	 * @return true if equal using the case rules
	 * @throws NullPointerException
	 *             if either string is null
	 */
	public static boolean checkEquals(String str1, String str2) {
		if (str1 == null || str2 == null) {
			throw new NullPointerException("The strings must not be null");
		}
		return str1.equals(str2);
	}

	/**
	 * Checks if one string starts with another using the case-sensitivity rule.
	 * 
	 */
	public static boolean checkStartsWith(String str, String start) {
		if (str == null || start == null) {
			throw new NullPointerException("The strings must not be null");
		}
		return str.startsWith(start);
	}

	/**
	 * Checks if one string ends with another using the case-sensitivity rule.
	 */
	public static boolean checkEndsWith(String str, String end) {
		if (str == null || end == null) {
			throw new NullPointerException("The strings must not be null");
		}
		return str.endsWith(end);
	}

	/**
	 * Determines whether the {@code parent} directory contains the
	 * {@code child} element (a file or directory).
	 * <p>
	 * The files names are expected to be normalized.
	 * </p>
	 * 
	 * Edge cases:
	 * <ul>
	 * <li>A {@code directory} must not be null: if null, throw
	 * IllegalArgumentException</li>
	 * <li>A directory does not contain itself: return false</li>
	 * <li>A null child file is not contained in any parent: return false</li>
	 * </ul>
	 * 
	 * @param canonicalParent
	 *            the file to consider as the parent.
	 * @param canonicalChild
	 *            the file to consider as the child.
	 * @return true is the candidate leaf is under by the specified composite.
	 *         False otherwise.
	 * @throws IOException
	 *             if an IO error occurs while checking the files.
	 * @since 2.2
	 * @see FileUtils#directoryContains(File, File)
	 */
	public static boolean directoryContains(final String canonicalParent,
			final String canonicalChild) throws IOException {

		// Fail fast against NullPointerException
		if (canonicalParent == null) {
			throw new IllegalArgumentException("Directory must not be null");
		}

		if (canonicalChild == null) {
			return false;
		}

		if (checkEquals(canonicalParent, canonicalChild)) {
			return false;
		}

		return checkStartsWith(canonicalChild, canonicalParent);
	}

	/**
	 * Determines if Windows file system is in use.
	 * 
	 * @return true if the system is Windows
	 */
	static boolean isSystemWindows() {
		return SYSTEM_SEPARATOR == WINDOWS_SEPARATOR;
	}

	public static void main(String[] args) {
		try {
			forceDelete(new File("d:\\to_delete\\tmp\\"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
